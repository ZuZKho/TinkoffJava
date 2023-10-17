package edu.project1;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleHangman {

    private final PrintStream printStream;
    private final Scanner scanner;

    ConsoleHangman(Scanner scanner, PrintStream printStream) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    public void run(String word) throws IllegalArgumentException {
        Session session = new Session(word);

        //  Check correctness of input
        try {
            session = new Session(word);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't start the game. "
                + "Illegal word to guess, only 5 different letters allowed, lower case.\n");
        }

        // Game cycle
        while (true) {
            printStream.print("Guess a letter:\n");
            String x = scanner.nextLine();

            if (x.equals("give up")) {
                x = "]";  // special symbol
            } else if (x.length() > 1 || (x.charAt(0) < 'a' && x.charAt(0) > 'z')) {
                printStream.print("Error: You have to enter small english letter or 'give up' phrase.\n");
                continue;
            }

            GuessResult result = tryGuess(session, x.charAt(0));
            if (result.getClass() == GuessResult.Win.class || result.getClass() == GuessResult.Defeat.class) {
                return;
            }
        }
    }

    private GuessResult tryGuess(Session session, char input) {
        GuessResult result = session.guess(input);
        printState(result);
        return result;
    }

    private void printState(GuessResult guess) {
        printStream.print(guess.message() + "\n");

        printStream.print("\n");
        printStream.print("The word: " + String.valueOf(guess.state()) + "\n");
        printStream.print("\n");

        if (guess.getClass() == GuessResult.Win.class) {
            printStream.print("You won!\n");
        } else if (guess.getClass() == GuessResult.Defeat.class) {
            printStream.print("You lost!\n");
        }
    }

}
