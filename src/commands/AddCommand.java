package commands;

import entity.Buku;
import program.IProgram;
import reader.BukuReadHelper;

import java.util.Objects;

public class AddCommand extends Command {
    private BukuReadHelper mReadHelper;

    public AddCommand(IProgram program) {
        super("Tambah Buku", program);
        mReadHelper = new BukuReadHelper(program.getReader());
    }

    @Override
    public void execute() {
        System.out.println("=== Menambah Buku ===");

        String kode = mReadHelper.readKode(newCode -> {
            if (mProgram.getBukuList().stream()
                    .anyMatch(buku -> Objects.equals(buku.kode(), newCode))) {
                System.out.println("Kode sudah digunakan");
                return false;
            }
            return true;
        });
        String judul = mReadHelper.readJudul("Judul: ");
        String penggarang = mReadHelper.readPenggarang("Penggarang: ");
        int tahunTerbit = mReadHelper.readTahunTerbit("Tahun Terbit: ");
        long harga = mReadHelper.readHarga("Harga: ");

        mProgram.getBukuList().add(new Buku(kode, judul, penggarang, tahunTerbit, harga));

        System.out.printf("Berhasil menambahkan buku %s\n", kode);
    }
}
