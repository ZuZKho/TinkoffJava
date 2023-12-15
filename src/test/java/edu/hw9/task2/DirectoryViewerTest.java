package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryViewerTest {

    DirectoryViewer directoryViewer = new DirectoryViewer();

    @Test
    void directoriesFindTest1() {
        List<File> expected = new ArrayList<>();
        expected.add(new File(".\\src\\main\\resources\\hw9"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5\\hello"));

        var ans = directoryViewer.bigDirectories("./src/main/resources/hw9", 1);

        assertEquals(expected.size(), ans.size());
        for (var file : expected) {
            assertTrue(ans.contains(file));
        }
    }

    @Test
    void directoriesFindTest2() {
        List<File> expected = new ArrayList<>();
        expected.add(new File(".\\src\\main\\resources\\hw9"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5"));

        var ans = directoryViewer.bigDirectories("./src/main/resources/hw9", 2);

        assertEquals(expected.size(), ans.size());
        for (var file : expected) {
            assertTrue(ans.contains(file));
        }
    }

    @Test
    void directoriesFindTest3() {
        var ans = directoryViewer.bigDirectories("./src/main/resources/hw9", 5);

        assertTrue(ans.isEmpty());
    }

    @Test
    void filesFindTest1() {
        List<File> expected = new ArrayList<>();
        expected.add(new File(".\\src\\main\\resources\\hw9\\python.py"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\python2.py"));

        var ans =
            directoryViewer.filesByPredicate("./src/main/resources/hw9", (File file) -> file.getName().endsWith(".py"));

        assertEquals(expected, ans);
    }

    @Test
    void filesFindTest2() {
        List<File> expected = new ArrayList<>();
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5\\file10.txt"));
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5\\hello\\world.txt"));

        var ans = directoryViewer.filesByPredicate("./src/main/resources/hw9", (File file) -> (file.length() > 1));

        assertEquals(expected, ans);
    }

    @Test
    void filesFindTest3() {
        List<File> expected = new ArrayList<>();
        expected.add(new File(".\\src\\main\\resources\\hw9\\test2\\test5\\file10.txt"));

        var ans = directoryViewer.filesByPredicate("./src/main/resources/hw9", (File file) -> (file.length() > 10));

        assertEquals(expected, ans);
    }

    @Test
    void filesFindTest4() {
        var ans = directoryViewer.filesByPredicate("./src/main/resources/hw9", (File file) -> (file.length() > 1000));

        assertEquals(0, ans.size());
    }
}
