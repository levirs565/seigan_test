package reader;

import java.util.function.Function;

public class BukuReadHelper {
    private MyReader mReader;

    public BukuReadHelper(MyReader reader) {
        mReader = reader;
    }

    public String readKode(Function<String, Boolean> extraCandidate) {
        String kode = "";
        while (true) {
            System.out.print("Kode: ");

            kode =  mReader.readLine();

            if (kode.length() > 4) {
                System.out.println("Kode maksimal 4 karakter");
                continue;
            }

            String finalKode = kode;
            if (!extraCandidate.apply(finalKode)) {
                continue;
            }

            break;
        }
        return kode;
    }

    public String readJudul(String prompt) {
        System.out.print(prompt);
        String judul = mReader.readLine();
        return judul;
    }

    public String readPenggarang(String prompt) {
        System.out.print(prompt);
        String penggarang = mReader.readLine();
        return penggarang;
    }

    public int readTahunTerbit(String prompt) {
        int tahunTerbit;
        while (true) {
            System.out.print(prompt);
            var value = mReader.readInt();
            if (value.isEmpty()) {
                System.out.println("Tahun terbit harus angka");
                continue;
            }

            if (value.get() <= 0) {
                System.out.println("Tahun terbit harus lebih dari 0");
                continue;
            }

            tahunTerbit = value.get();
            break;
        }
        return tahunTerbit;
    }

    public long readHarga(String prompt) {
        long harga;
        while (true) {
            System.out.print(prompt);
            var value = mReader.readLong();
            if (value.isEmpty()) {
                System.out.println("Harga harus angka");
                continue;
            }

            if (value.get() <= 0) {
                System.out.println("Harga harus lebih dari 0");
                continue;
            }

            harga = value.get();
            break;
        }
        return harga;
    }
}
