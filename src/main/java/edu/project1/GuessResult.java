package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {

    String HIT = "Hit!";

    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    private static String mistakeMessage(int attempt, int maxAttempts) {
        return String.format("Missed, mistake %d out of %d.", attempt, maxAttempts);
    }

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @NotNull public String message() {
            return mistakeMessage(attempt, maxAttempts);
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @NotNull public String message() {
            return HIT;
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @NotNull public String message() {
            return HIT;
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @NotNull public String message() {
            return mistakeMessage(attempt, maxAttempts);
        }
    }
}
