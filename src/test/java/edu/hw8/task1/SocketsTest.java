package edu.hw8.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SocketsTest {

    final int port = 18676;

    static final HashMap<String, String> questions = new HashMap<>();

    @BeforeAll
    static void initialization() {
        questions.put("личности", "Не переходи на личности там, где их нет");
        questions.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        questions.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        questions.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
        questions.put("корова", "-");
        questions.put("завтра", "-");
    }

    private String getRandomQuestion() {
        List<String> keysList = new ArrayList<>(questions.keySet());
        int randomIndex = new Random().nextInt(keysList.size());
        return keysList.get(randomIndex);
    }

    private void testHelper(int nThreads, int clientsCount) {

        Server server = new Server();
        Thread serverThread = new Thread(() -> {
           server.start(nThreads, port);
        });
        serverThread.start();

        Thread[] threads = new Thread[clientsCount];
        for (int i = 0; i < clientsCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    String question = getRandomQuestion();
                    var response = OneQueryClient.query(question, port);
                    assertEquals(questions.get(question), response);
                } catch(Exception e) {
                    fail();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < clientsCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                fail();
            }
        }

        server.terminate();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            fail();
        }
    }


    @Test
    void test1() {
        testHelper(1, 1);
    }

    @Test
    void test2() {
        testHelper(5, 5);
    }

    @Test
    void test3() {
        testHelper(5, 30);
    }
}
