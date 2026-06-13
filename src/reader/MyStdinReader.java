package reader;

import java.util.Optional;
import java.util.Scanner;

public class MyStdinReader implements MyReader {
    private final Scanner mScanner = new Scanner(System.in);

    public String readLine() {
        return mScanner.nextLine();
    }

    public Optional<Integer> readInt() {
        String line = mScanner.nextLine();
        try {
            return Optional.of(Integer.parseInt(line));
        } catch (NumberFormatException _) {
            return Optional.empty();
        }
    }

    public Optional<Long> readLong() {
        String line = mScanner.nextLine();
        try {
            return Optional.of(Long.parseLong(line));
        } catch (NumberFormatException _) {
            return Optional.empty();
        }
    }
}
