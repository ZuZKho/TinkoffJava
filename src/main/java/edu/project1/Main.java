package edu.project1;

import java.io.PrintStream;
import java.util.Scanner;

class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);
        var game = new ConsoleHangman(scanner, printStream);
        game.run(null);
    }
}
