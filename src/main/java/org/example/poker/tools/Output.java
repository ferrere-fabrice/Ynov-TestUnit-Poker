package org.example.poker.tools;

import org.example.poker.table.Dealer;

public class Output {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void PrintStartMessageTest(){
        System.out.println(ANSI_BLUE+ Thread.currentThread().getStackTrace()[2].getMethodName() + ANSI_RESET);
    }


    public static void PrintEndMessageTest(Dealer dealer){
        System.out.println(dealer.toString());
        System.out.println(ANSI_BLACK + "===================================" + ANSI_WHITE);
    }


}
