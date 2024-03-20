package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {

    private static  Fork fork0 = new Fork("0");
    private static   Fork fork1 = new Fork("1");
    private static   Fork fork2 = new Fork("2");
    private static   Fork fork3 = new Fork("3");
    private static  Fork fork4 = new Fork("4");

    public static ArrayList<Fork> table = new ArrayList<>(Arrays.asList(fork0,fork1,fork2,fork3,fork4));

    public static void main(String[] args) {

        Philosopher philosopher1 = new Philosopher("0");
        Philosopher philosopher2 = new Philosopher("1");
        Philosopher philosopher3 = new Philosopher("2");
        Philosopher philosopher4 = new Philosopher("3");
        Philosopher philosopher5 = new Philosopher("4");

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }
}
