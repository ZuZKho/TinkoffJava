package edu.hw10.task2.Fibonacci;

import edu.hw10.task2.Cache.Cache;

public interface FibCalculatorInterface {

    @Cache
    long fib(int number);
}
