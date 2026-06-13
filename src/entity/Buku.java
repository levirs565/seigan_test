package entity;

import java.io.Serializable;

public record Buku(
        String kode,
        String judul,
        String penggarang,
        int tahunTerbit,
        long harga
) implements Serializable {
}
