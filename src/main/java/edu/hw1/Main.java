package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

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
        int mm, ss;

        try {
            mm = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return -1;
        }

        try {
            ss = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (ss < 0 || mm < 0 || ss > 59) {
            return -1;
        }

        return mm * 60 + ss;
    }

    static public int countDigits(int number) {
        int cnt = 1;

        while (number >= 10) {
            cnt++;
            number /= 10;
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
        if (n == 6174) {
            return 0;
        }

        String str = String.valueOf(n);
        while (str.length() < 4) {
            str = "0".concat(str);
        }

        char[] strChar = str.toCharArray();
        Arrays.sort(strChar);

        int first = Integer.parseInt(String.valueOf(strChar));

        for (int i = 0; i < 2; i++) {
            char tmp = strChar[i];
            strChar[i] = strChar[3 - i];
            strChar[3 - i] = tmp;
        }

        int second = Integer.parseInt(String.valueOf(strChar));

        if (second > first) {
            int tmp = second;
            second = first;
            first = tmp;
        }

        return countK(first - second) + 1;
    }

    static public int rotateRight(int n, int shift) {
        int bits = 0;
        for (int i = 0; i < 32; i++) {
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

    static public int rotateLeft(int n, int shift) {
        int bits = 0;
        for (int i = 0; i < 32; i++) {
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
        int[] xes = new int[] {1, -1, 2, -2, 1, -1, 2, -2};
        int[] yes = new int[] {2, 2, 1, 1, -2, -2, -1, -1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {

                    if (arr[i][j] == 1 &&
                        i + xes[k] < 8 && i + xes[k] >= 0 &&
                        j + yes[k] < 8 && j + yes[k] >= 0) {

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
