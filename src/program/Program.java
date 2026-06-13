package program;

import commands.*;
import entity.Buku;
import reader.MyReader;
import reader.MyStdinReader;

import java.util.ArrayList;

public class Program implements IProgram {
    private boolean mIsExit = false;
    private BukuManager mManager = new BukuManager();
    private ArrayList<Command> mCommands = new ArrayList<>();
    private MyReader mReader = new MyStdinReader();

    public Program() {
        mCommands.add(new ListCommand(this));
        mCommands.add(new AddCommand(this));
        mCommands.add(new EditCommand(this));
        mCommands.add(new DeleteCommand(this));
        mCommands.add(new ExitCommand(this));
    }

    public void run() {
        while (!mIsExit) {
            System.out.println("Opsi: ");
            int index = 1;
            for (Command cmd : mCommands) {
                System.out.printf("%d. %s\n", index, cmd.getName());
                index++;
            }
            System.out.print("Masukkan Pilihanmu: ");
            var pilihan = mReader.readInt();
            if (pilihan.isEmpty() || pilihan.get() <= 0 || pilihan.get() > mCommands.size()) {
                System.out.println("Pilihan tidak valid");
                continue;
            }

            mCommands.get(pilihan.get() - 1).execute();
            System.out.println();
        }

        if (mManager.save()) {
            System.out.println("Berhasil disimpan");
        } else {
            System.out.println("Berhasil ditulis");
        }
    }

    @Override
    public MyReader getReader() {
        return mReader;
    }

    @Override
    public ArrayList<Buku> getBukuList() {
        return mManager.mList;
    }

    @Override
    public void exit() {
        mIsExit = true;
    }


}
