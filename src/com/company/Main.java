package com.company;

public class Main {

    public static void main(String[] args) {

        int X = 45;
        int result;

        result = fibRecur(X);

        System.out.println("\n"+result+"\n");

    }

    public static int fibRecur( int X) {

        if ( X == 0 || X == 1 ){
            return X;
        } else {
            return fibRecur(X - 1) + fibRecur( X - 2);
        }

    }
}

