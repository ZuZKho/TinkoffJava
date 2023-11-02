package edu.hw3;

import java.util.List;

public class Task4 {

    private Task4() {
    }

    private static final int BASE = 10;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int NINE = 9;

    private static final int MAXROMAN = 3999;

    private static String getDigit(int a, String one, String five, String ten) {
        String answer = "";

        switch (a) {
            case FOUR:
                answer = one + five;
                break;
            case NINE:
                answer = one + ten;
                break;
            default:
                int cur = a;
                if (cur >= FIVE) {
                    answer = five;
                    cur -= FIVE;
                }
                while (cur != 0) {
                    answer = answer.concat(one);
                    cur--;
                }
                break;
        }

        return answer;
    }

    public static String convertToRoman(int number) {
        if (number > MAXROMAN) {
            return null;
        }

        String answer = "";
        int a = number;
        List<String> ones = List.of("I", "X", "C", "M");
        List<String> fives = List.of("V", "L", "D", "");
        List<String> tens = List.of("X", "C", "M", "");

        for (int i = 0; i < FOUR; i++) {
            if (a > 0) {
                int curNumber = a % BASE;
                a /= BASE;
                answer = getDigit(curNumber, ones.get(i), fives.get(i), tens.get(i)).concat(answer);
            }
        }

        return answer;
    }
}
