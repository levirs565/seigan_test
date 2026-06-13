package program;

import entity.Buku;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

public class BukuManager {
    public ArrayList<Buku> mList = new ArrayList<>();
    private final String mFileName = "buku.txt";
    private final File mFile = new File(mFileName);
    private String mSalt = "1234567890123456";
    private String mPassword = "password";
    private String mAlgorithm = "AES/CBC/PKCS5Padding";
    private SecretKey mKey;

    private static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey originalKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return originalKey;
    }

    public BukuManager() {
        try {
            mKey = getKeyFromPassword(mPassword, mSalt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        if (mFile.exists()) {
            try (FileInputStream stream = new FileInputStream(mFile)) {
                Cipher cipher = Cipher.getInstance(mAlgorithm);

                byte[] fileIv = new byte[16];
                stream.read(fileIv);
                cipher.init(Cipher.DECRYPT_MODE, mKey, new IvParameterSpec(fileIv));

                try (CipherInputStream cipherIn = new CipherInputStream(stream, cipher);
                     ObjectInputStream objectStream = new ObjectInputStream(cipherIn)) {
                    Object object = objectStream.readObject();
                    if (object instanceof List<?> list) {
                        for (Object item : list) {
                            if (item instanceof Buku buku) mList.add(buku);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Gagal membaca objek.");
                    e.printStackTrace();
                }
            } catch (FileNotFoundException _) {

            } catch (IOException e) {
                System.out.println("Gagal membaca file.");
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean save() {
        try (FileOutputStream stream = new FileOutputStream(mFile)) {
            Cipher cipher = Cipher.getInstance(mAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, mKey);
            byte[] iv = cipher.getIV();

            stream.write(iv);

            try (CipherOutputStream cipherOut = new CipherOutputStream(stream, cipher);
                 ObjectOutputStream objectStream = new ObjectOutputStream(cipherOut)) {
                objectStream.writeObject(mList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
