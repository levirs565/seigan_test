package commands;

import entity.Buku;
import program.IProgram;
import reader.BukuReadHelper;

import java.util.Objects;
import java.util.stream.IntStream;

public class EditCommand extends Command {
    private BukuReadHelper mReadHelper;

    public EditCommand(IProgram program) {
        super("Ubah Buku", program);
        mReadHelper = new BukuReadHelper(program.getReader());
    }

    @Override
    public void execute() {
        System.out.println("=== Mengubah Buku ===");

        String kode = mReadHelper.readKode(oldKode -> {
            if (mProgram.getBukuList().stream()
                    .noneMatch(buku -> Objects.equals(buku.kode(), oldKode))) {
                System.out.println("Kode tidak ditemukan");
                return false;
            }
            return true;
        });
        var oldIndex = IntStream.range(0, mProgram.getBukuList().size())
                .filter(index ->
                        Objects.equals(mProgram.getBukuList().get(index).kode(), kode))
                .findFirst();
        if (oldIndex.isEmpty()) {
            System.out.println("Harusnya gak mungkin terjadi");
            return;
        }

        Buku oldBuku = mProgram.getBukuList().get(oldIndex.getAsInt());
        String judul = mReadHelper.readJudul(String.format("Judul (%s): ", oldBuku.judul()));
        String penggarang = mReadHelper.readPenggarang(String.format("Penggarang (%s): ", oldBuku.penggarang()));
        int tahunTerbit = mReadHelper.readTahunTerbit(String.format("Tahun Terbit (%d): ", oldBuku.tahunTerbit()));
        long harga = mReadHelper.readHarga(String.format("Harga (%d): ", oldBuku.harga()));

        mProgram.getBukuList().set(oldIndex.getAsInt(), new Buku(kode, judul, penggarang, tahunTerbit, harga));

        System.out.printf("Berhasil mengubah buku %s\n", kode);
    }
}
