package org.example;
public class Main {
    public static double pow(double x, int n) {
        if (n == 0) {
            return 1;

        }

        return x * pow(x, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 3));
    }
}