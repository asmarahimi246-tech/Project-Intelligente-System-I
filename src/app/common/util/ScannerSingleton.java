package app.common.util;

import java.util.Scanner;

/**
 * ScannerSingleton
 * 
 * needed because clossing a scanner closses system.in
 */
public class ScannerSingleton {
    private static Scanner INSTANCE;

    /**
     * Constructor
     */
    private ScannerSingleton() {
    }

    /**
     * get a scanner instance
     * 
     * @return the instance
     */
    public static Scanner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Scanner(System.in);
        }
        return INSTANCE;
    }

    /**
     * closes the scanner
     */
    public static void closeInstance() {
        if (INSTANCE == null) {
            INSTANCE.close();
        }
    }
}