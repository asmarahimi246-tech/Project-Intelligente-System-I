package org.example;

public class Big {
    public static int factorial(int n){
        int result = 1;

        for (int i = 1; i <=n; i++){
            result = result * i;
        }

        return result;

    }

    static void main(String[] args) {
        System.out.println(factorial(5));
    }

}



