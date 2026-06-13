package program;

import entity.Buku;
import reader.MyReader;

import java.util.ArrayList;

public interface IProgram {
    MyReader getReader();

    ArrayList<Buku> getBukuList();

    void exit();
}
