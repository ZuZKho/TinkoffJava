package edu.hw1;

class Task2 {

    private Task2() {}

    static public int countDigits(int numberInput) {
        int cnt = 1;
        int number = numberInput;

        final int BASE = 10;
        while (number >= BASE) {
            cnt++;
            number /= BASE;
        }

        return cnt;
    }
}
