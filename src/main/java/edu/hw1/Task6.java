package edu.hw1;

import java.util.Arrays;

class Task6 {

    private Task6() {}

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
}
