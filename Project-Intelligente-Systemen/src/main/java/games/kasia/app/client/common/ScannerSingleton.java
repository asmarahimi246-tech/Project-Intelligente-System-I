package games.kasia.app.client.common;

import java.util.Scanner;

/**
 * ScannerSingleton
 * 
 * needed because clossing a scanner closses system.in
 */
public class ScannerSingleton {
    private static Scanner instance;

    /**
     * Constructor
     */
    private ScannerSingleton() {
    }

    /**
     * get a scanner instance
     * 
     * @return
     */
    public static Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

    /**
     * closes the scanner
     */
    public static void closeInstance() {
        if (instance == null) {
            instance.close();
        }
    }
}