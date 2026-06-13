package program;

import entity.Buku;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BukuManager {
    public ArrayList<Buku> mList = new ArrayList<>();
    private final String mFileName = "buku.txt";
    private final File mFile = new File(mFileName);

    public BukuManager() {
        if (mFile.exists()) {
            try (FileInputStream stream = new FileInputStream(mFile)) {
                try (ObjectInputStream objectStream = new ObjectInputStream(stream)) {
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
            }

        }
    }

    public boolean save() {
        try (FileOutputStream stream = new FileOutputStream(mFile)) {
            try (ObjectOutputStream objectStream = new ObjectOutputStream(stream)) {
                objectStream.writeObject(mList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
