package commands;

import program.IProgram;
import reader.BukuReadHelper;

import java.util.Objects;

public class DeleteCommand extends Command {
    private BukuReadHelper mReadHelper;

    public DeleteCommand(IProgram program) {
        super("Hapus Buku", program);
        mReadHelper = new BukuReadHelper(program.getReader());
    }

    @Override
    public void execute() {
        System.out.println("=== Menghapus Buku ===");

        String kode = mReadHelper.readKode(oldKode -> {
            if (mProgram.getBukuList().stream()
                    .noneMatch(buku -> Objects.equals(buku.kode(), oldKode))) {
                System.out.println("Kode tidak ditemukan");
                return false;
            }
            return true;
        });
        var oldItem = mProgram.getBukuList().stream()
                .filter(buku ->
                        buku.kode().equals(kode))
                .findFirst();
        if (oldItem.isEmpty()) {
            System.out.println("Harusnya gak mungkin terjadi");
            return;
        }

        mProgram.getBukuList().remove(oldItem.get());
        System.out.printf("Berhasil menghapus buku %s\n", kode);
    }
}
