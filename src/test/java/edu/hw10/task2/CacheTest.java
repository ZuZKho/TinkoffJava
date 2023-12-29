package edu.hw10.task2;

import edu.hw10.task2.Cache.CacheProxy;
import edu.hw10.task2.Fibonacci.FibCalculator;
import edu.hw10.task2.Fibonacci.FibCalculatorInterface;
import edu.hw10.task2.Strings.StringReverse;
import edu.hw10.task2.Strings.StringReverseInterface;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {

    @Test
    // Тест основна на замерке времени долгих вычислений. При кэшировании они должны выполнятся моментально.
    // Сделал так, чтобы не изменять внутренности класса fibonacci
    void fibTest1() {
        FibCalculatorInterface c = new FibCalculator();
        FibCalculatorInterface proxy = (FibCalculatorInterface) CacheProxy.create(c, c.getClass());

        // Рекурсия внутри вызова fib не кэшируется. Кэшируется только самый первый вызов.
        Instant first = Instant.now();
        for(int i = 0; i < 37; i++) {
            proxy.fib(i);
        }
        Instant second = Instant.now();
        for(int i = 0; i < 37; i++) {
            proxy.fib(i);
        }
        Instant third = Instant.now();
        Duration diff1 = Duration.between(first, second);
        Duration diff2 = Duration.between(second, third);

        assertTrue(diff1.toNanos() > diff2.toNanos() * 100);
    }

    @Test
    // Используется специальный тестовый класс, который сообщает был ли вызван метод.
    void stringNoPersistTest1() {
        StringReverseInterface c = new StringReverse();
        StringReverseInterface proxy = (StringReverseInterface) CacheProxy.create(c, c.getClass());

        // Вызываем первый раз
        String ans1 = proxy.cacheNoPersistReverse("abcdef");

        // Вызываем второй раз
        proxy.clearCalled();
        String ans2 = proxy.cacheNoPersistReverse("abcdef");

        // Проверяем, что результат из кэша
        assertFalse(proxy.wasCalled());
    }

    @Test
    void stringNoCacheTest1() {
        StringReverseInterface c = new StringReverse();
        StringReverseInterface proxy = (StringReverseInterface) CacheProxy.create(c, c.getClass());

        // Вызываем первый раз
        String ans1 = proxy.nocacheReverse("abcdef");

        // Вызываем второй раз
        proxy.clearCalled();
        String ans2 = proxy.nocacheReverse("abcdef");

        // Проверяем, что результат не из кэша
        assertTrue(proxy.wasCalled());
    }

    @Test
    void stringPersistTest1() {
        StringReverseInterface c = new StringReverse();
        StringReverseInterface proxy = (StringReverseInterface) CacheProxy.create(c, c.getClass());

        // Вызываем первый раз
        String ans1 = proxy.cachePersistReverse("abcde");

        // Вызываем второй раз
        proxy.clearCalled();
        String ans2 = proxy.cachePersistReverse("abcde");

        // Проверяем, что результат из кэша
        assertFalse(proxy.wasCalled());
    }
}
