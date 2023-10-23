package edu.project1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleHangmanTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outContent);

    @Test
    @DisplayName("Guessing word potato with mistakes")
    void runPotatoTest1() throws Exception {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/test/java/edu/project1/data/input1.txt"));
        } catch (Exception e) {
            throw new Exception("Can't open test source file!");
        }

        String expectedOutput =
            Files.readString(Paths.get("src/test/java/edu/project1/data/output1.txt")).replaceAll("\r", "");

        var game = new ConsoleHangman(scanner, printStream);
        game.run("potato");

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Not Guessing word potato")
    void runPotatoTest2() throws Exception {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/test/java/edu/project1/data/input2.txt"));
        } catch (Exception e) {
            throw new Exception("Can't open test source file!");
        }

        var game = new ConsoleHangman(scanner, printStream);
        game.run("potato");

        String expectedOutput =
            Files.readString(Paths.get("src/test/java/edu/project1/data/output2.txt")).replaceAll("\r", "");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Illegal argument test")
    void illegalArgumentTest() throws Exception {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/test/java/edu/project1/data/input2.txt"));
        } catch (Exception e) {
            throw new Exception("Can't open test source file!");
        }

        var game = new ConsoleHangman(scanner, printStream);
        assertThrows(IllegalArgumentException.class, () -> game.run("abcdef"));
    }

    @Test
    @DisplayName("Too many mistakes test")
    void tooManyMistakesTest() throws Exception {
        Session session = new Session("abc");
        session.guess('d');
        session.guess('d');
        session.guess('d');
        GuessResult result1 = session.guess('d');
        assertEquals(result1.getClass(), GuessResult.FailedGuess.class);
        GuessResult result2 = session.guess('d');
        assertEquals(result2.getClass(), GuessResult.Defeat.class);
    }

    @Test
    @DisplayName("Correct state change")
    void correctStateChangeTest() throws Exception {
        Session session = new Session("abc");
        GuessResult result1 = session.guess('d');
        assertEquals(1, result1.attempt());
        assertEquals("***", String.valueOf(result1.state()));
        GuessResult result2 = session.guess('a');
        assertEquals(1, result2.attempt());
        assertEquals("a**", String.valueOf(result2.state()));
    }

    @Test
    @DisplayName("Typo with give up")
    void runTypoGiveUp() throws Exception {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/test/java/edu/project1/data/input3.txt"));
        } catch (Exception e) {
            throw new Exception("Can't open test source file!");
        }

        String expectedOutput =
            Files.readString(Paths.get("src/test/java/edu/project1/data/output3.txt")).replaceAll("\r", "");

        var game = new ConsoleHangman(scanner, printStream);
        game.run("aboba");

        assertEquals(expectedOutput, outContent.toString());
    }
}
