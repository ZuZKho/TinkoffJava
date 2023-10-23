package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {

    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts = 5;

    private int attempts;

    private boolean allowed(String word) {
        final int englishLettersCount = 26;
        int[] cnt = new int[englishLettersCount];
        int differentLettersCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) > 'z' || word.charAt(i) < 'a') {
                return false;
            }
            int letterIndex = word.charAt(i) - 'a';
            if (cnt[letterIndex] == 0) {
                differentLettersCount++;
            }
            cnt[letterIndex]++;
        }

        return differentLettersCount <= this.maxAttempts;
    }

    public Session(String word) {
        if (word == null) {
            this.answer = DictionaryInterface.randomWord();
        } else {
            if (allowed(word)) {
                this.answer = word;
            } else {
                throw new IllegalArgumentException("Invalid word!!!");
            }
        }

        this.userAnswer = new char[this.answer.length()];
        for (int i = 0; i < this.answer.length(); i++) {
            this.userAnswer[i] = '*';
        }
        this.attempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        // special char to give up
        if (guess == ']') {
            return giveUp();
        }

        // is some letter guessed on this guess
        boolean flag = false;
        // is word completely guessed
        boolean completed = true;

        for (int i = 0; i < this.answer.length(); i++) {
            if (answer.charAt(i) == guess && userAnswer[i] == '*') {
                flag = true;
                userAnswer[i] = guess;
            }
            if (userAnswer[i] == '*') {
                completed = false;
            }
        }

        // All word was guessed
        if (completed) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts);
        }

        // Some chars was guessed
        if (flag) {
            return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts);
        }

        // Mistake, need to increase attempts
        this.attempts++;

        return this.attempts == this.maxAttempts
            ? new GuessResult.Defeat(userAnswer, attempts, maxAttempts)
            : new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer, attempts, maxAttempts);
    }

}
