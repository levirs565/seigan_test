package reader;

import java.util.Optional;

public interface MyReader {
    String readLine();
    Optional<Integer> readInt();
    Optional<Long> readLong();
}
