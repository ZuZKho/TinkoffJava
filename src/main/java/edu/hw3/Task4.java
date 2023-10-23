package edu.hw3;

public class Task4 {

    private Task4() {
    }

    private static final int BASE = 10;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;

    private static String getDigit(int a, String one, String five, String ten) {
        String answer = "";

        switch (a) {
            case ONE:
                answer = one;
                break;
            case TWO:
                answer = one + one;
                break;
            case THREE:
                answer = one + one + one;
                break;
            case FOUR:
                answer = one + five;
                break;
            case FIVE:
                answer = five;
                break;
            case SIX:
                answer = five + one;
                break;
            case SEVEN:
                answer = five + one + one;
                break;
            case EIGHT:
                answer = five + one + one + one;
                break;
            case NINE:
                answer = one + ten;
                break;
            default:
                break;
        }
        return answer;
    }

    public static String convertToRoman(int number) {
        String answer = "";
        int a = number;

        if (a > 0) {
            int curNumber = a % BASE;
            a /= BASE;
            answer = getDigit(curNumber, "I", "V", "X");
        }

        if (a > 0) {
            int curNumber = a % BASE;
            a /= BASE;
            answer = getDigit(curNumber, "X", "L", "C") + answer;
        }

        if (a > 0) {
            int curNumber = a % BASE;
            a /= BASE;
            answer = getDigit(curNumber, "C", "D", "M") + answer;
        }

        if (a > 0) {
            int curNumber = a % BASE;
            a /= BASE;
            switch (curNumber) {
                case ONE:
                    answer = "M" + answer;
                    break;
                case TWO:
                    answer = "MM" + answer;
                    break;
                case THREE:
                    answer = "MMM" + answer;
                    break;
                default:
                    return null; // Слишком большое число
            }
        }

        if (a > 0) {
            return null; // такое большое число нельзя записать римской записью
        }

        return answer;
    }
}
