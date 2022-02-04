package global;

import java.util.Scanner;

public class ScannerGlobal {
    private static Scanner sc = new Scanner(System.in);

    private ScannerGlobal() {}

    public static Scanner getScanner() {
        return sc;
    }
}
