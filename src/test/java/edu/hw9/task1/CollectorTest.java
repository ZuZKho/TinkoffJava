package edu.hw9.task1;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectorTest {

    private boolean isMapEquals(Map<String, Statistics> map1, Map<String, Statistics> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }

        for(var entry : map1.entrySet()) {
            Statistics stat1 = entry.getValue();
            Statistics stat2 = map2.get(entry.getKey());

            final double eps = 0.000001;
            if ((stat1.sum() - stat2.sum()) > eps) {
                return false;
            }
            if (stat1.count() != stat2.count()) {
                return false;
            }
            if ((stat1.average() - stat2.average()) > eps) {
                return false;
            }
            if ((stat1.maximum() - stat2.maximum()) > eps) {
                return false;
            }
            if ((stat1.minimum() - stat2.minimum()) > eps) {
                return false;
            }
        }

        return true;
    }

    @Test
    void singleThreadTest() throws InterruptedException {
        HashMap<String, Statistics> expected = new HashMap<>();
        expected.put("active_time", new Statistics(7.97, 9, 0.88555555556, 5.1, 0.05));
        expected.put("cash_amount", new Statistics(54, 4, 10.25, 15, 12));

        Collector collector = new Collector(1);
        collector.push("active_time", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
        collector.push("cash_amount", new double[]{12, 13, 14, 15});
        collector.push("active_time", new double[]{0.2, 0.3, 0.4, 0.12});
        Thread.sleep(10); // Чтобы все успело досчитаться
        Map<String, Statistics> answer = collector.getStatistics();

        assertTrue(isMapEquals(expected, answer));
    }

    @Test
    void multiThreadTest() throws InterruptedException {
        HashMap<String, Statistics> expected = new HashMap<>();
        expected.put("active_time", new Statistics(3985, 4500, 0.88555555555556, 5.1, 0.05));
        expected.put("cash_amount", new Statistics(27000, 2000, 10.25, 15, 12));

        Collector collector = new Collector(5);
        Thread threads[] = new Thread[25];
        for(int i = 0; i < 25; i++) {
            threads[i] = new Thread(() -> {
               for (int j = 0; j < 20; j++) {
                   collector.push("active_time", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
                   collector.push("cash_amount", new double[]{12, 13, 14, 15});
                   collector.push("active_time", new double[]{0.2, 0.3, 0.4, 0.12});
               }
            });
            threads[i].start();
        }
        Thread.sleep(100); // Чтобы все успело досчитаться

        Map<String, Statistics> answer = collector.getStatistics();
        assertTrue(isMapEquals(expected, answer));
    }


}
