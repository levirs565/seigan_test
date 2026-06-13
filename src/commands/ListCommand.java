package commands;

import entity.Buku;
import program.IProgram;

public class ListCommand extends Command {
    public ListCommand(IProgram program) {
        super("Daftar Buku", program);
    }

    @Override
    public void execute() {
        System.out.println("=== Menampilkan Daftar Buku ===");
        int index = 1;
        for (Buku item : mProgram.getBukuList()) {
            System.out.printf(
                    "%d. Kode : %s | Judul : %s | Pengarang : %s | Tahun Rilis : %d | Harga : %d\n",
                    index,
                    item.kode(),
                    item.judul(),
                    item.penggarang(),
                    item.tahunTerbit(),
                    item.harga()
            );
            index++;
        }
    }
}
