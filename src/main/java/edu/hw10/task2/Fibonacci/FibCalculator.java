package edu.hw10.task2.Fibonacci;

public class FibCalculator implements FibCalculatorInterface {
    public long fib(int number) {
        if (number < 2) {
            return 1;
        }
        return this.fib(number - 1) + this.fib(number - 2);
    }
}
