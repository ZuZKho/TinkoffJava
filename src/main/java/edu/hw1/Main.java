package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class Main {

    private Main() {
    }

    static public void main(String[] args) {
        task0();
    }

    static public void task0() {
        Logger logger = Logger.getLogger("logger");
        logger.info("Hello, world!");
    }

    static public int minutesToSeconds(String time) {
        String[] parts = time.split(":");

        if (parts.length > 2) {
            return -1;
        }
        int mm;
        int ss;

        try {
            mm = Integer.parseInt(parts[0]);
            ss = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        final int secondsInMinute = 60;
        if (ss < 0 || mm < 0 || ss >= secondsInMinute) {
            return -1;
        }

        return mm * secondsInMinute + ss;
    }

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

    static public boolean isNestable(Integer[] firstArr, Integer[] secondArr) {
        ArrayList<Integer> firstList = new ArrayList<Integer>(Arrays.asList(firstArr));
        ArrayList<Integer> secondList = new ArrayList<Integer>(Arrays.asList(secondArr));

        int firstMin = Collections.min(firstList);
        int firstMax = Collections.max(firstList);

        int secondMin = Collections.min(secondList);
        int secondMax = Collections.max(secondList);

        return firstMin > secondMin && firstMax < secondMax;
    }

    static public String fixString(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i + 1 < strChar.length; i += 2) {
            char tmp = strChar[i];
            strChar[i] = strChar[i + 1];
            strChar[i + 1] = tmp;
        }
        return new String(strChar);
    }

    static public boolean isPalindromeDescendant(int number) {
        String str = String.valueOf(number);
        if (str.length() < 2) {
            return false;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {

                String descendant = "";
                for (int j = 0; j + 1 < str.length(); j += 2) {
                    int z1 = str.charAt(j) - '0';
                    int z2 = str.charAt(j + 1) - '0';
                    descendant = descendant.concat(String.valueOf(z1 + z2));
                }
                if (str.length() % 2 == 1) {
                    descendant = descendant.concat(String.valueOf(str.charAt(str.length() - 1)));
                }

                return isPalindromeDescendant(Integer.parseInt(descendant));
            }
        }

        return true;
    }

    static public int countK(int n) {
        final int kaprekarNumber = 6174;
        final int nLen = 4;

        if (n == kaprekarNumber) {
            return 0;
        }

        String str = String.valueOf(n);
        while (str.length() < nLen) {
            str = "0".concat(str);
        }

        char[] strChar = str.toCharArray();
        Arrays.sort(strChar);

        int first = Integer.parseInt(String.valueOf(strChar));

        for (int i = 0; i < nLen / 2; i++) {
            char tmp = strChar[i];
            strChar[i] = strChar[nLen - i - 1];
            strChar[nLen - i - 1] = tmp;
        }

        int second = Integer.parseInt(String.valueOf(strChar));

        if (second > first) {
            int tmp = second;
            second = first;
            first = tmp;
        }

        return countK(first - second) + 1;
    }

    static public int rotateRight(int nInput, int shiftInput) {
        final int intBits = 32;
        int n = nInput;
        int shift = shiftInput;

        int bits = 0;
        for (int i = 0; i < intBits; i++) {
            if ((n & (1 << i)) != 0) {
                bits = i;
            }
        }

        while (shift != 0) {
            boolean flag = false;
            if ((n & 1) != 0) {
                flag = true;
            }
            n >>= 1;
            if (flag) {
                n |= (1 << bits);
            }
            shift--;
        }

        return n;
    }

    static public int rotateLeft(int nInput, int shiftInput) {
        final int intBits = 32;
        int n = nInput;
        int shift = shiftInput;

        int bits = 0;
        for (int i = 0; i < intBits; i++) {
            if ((n & (1 << i)) != 0) {
                bits = i;
            }
        }

        while (shift != 0) {
            boolean flag = false;
            if ((n & (1 << bits)) != 0) {
                n ^= (1 << bits);
                flag = true;
            }
            n <<= 1;
            if (flag) {
                n |= 1;
            }
            shift--;
        }

        return n;
    }

    static public boolean knightBoardCapture(int[][] arr) {
        final int step2 = 2;
        final int step1 = 1;
        final int sideLen = 8;

        int[] xes = new int[] {step1, -step1, step2, -step2, step1, -step1, step2, -step2};
        int[] yes = new int[] {step2, step2, step1, step1, -step2, -step2, -step1, -step1};

        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++) {
                for (int k = 0; k < sideLen; k++) {

                    if (arr[i][j] == 1
                        && i + xes[k] < sideLen && i + xes[k] >= 0
                        && j + yes[k] < sideLen && j + yes[k] >= 0) {

                        if (arr[i + xes[k]][j + yes[k]] == 1) {
                            return false;
                        }

                    }
                }
            }
        }

        return true;
    }
}
