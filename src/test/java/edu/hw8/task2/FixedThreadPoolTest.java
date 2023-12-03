package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class FixedThreadPoolTest {

    double[] fibonacci(int nThreads, int nFib) throws Exception {
        double[] fib = new double[nFib];

        double phi = 1.618034;
        double sqrt5 = Math.sqrt(5);

        try (ThreadPool executor = FixedThreadPool.create(nThreads)) {
            executor.start();
            for(int i = 0; i < nFib; i++) {
                final int cur = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        fib[cur] = Math.round((Math.pow(phi, cur) - Math.pow(1 - phi, cur)) / sqrt5);
                    }
                });
            }
            executor.join();
        }

        return fib;
    }

    @Test
    void fibonacciSmall1() {
        assertDoesNotThrow(() -> {
            var ans = fibonacci(1, 10);
            double[] expected = new double[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

            assertArrayEquals(expected, ans);
        });
    }

    @Test
    void fibonacciSmall5() {
        assertDoesNotThrow(() -> {
            var ans = fibonacci(5, 10);
            double[] expected = new double[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

            assertArrayEquals(expected, ans);
        });
    }

    @Test
    void fibonacciBig1() {
        assertDoesNotThrow(() -> {
            var ans = fibonacci(1, 1000000);
            double[] expected = new double[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

            assertArrayEquals(expected, Arrays.stream(ans).limit(10).toArray());
        });
    }

    @Test
    void fibonacciBig5() {
        assertDoesNotThrow(() -> {
            var ans = fibonacci(5, 1000000);
            double[] expected = new double[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

            assertArrayEquals(expected, Arrays.stream(ans).limit(10).toArray());
        });
    }

}
