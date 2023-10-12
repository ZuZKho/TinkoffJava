package edu.hw1;

class Task7 {

    private Task7() {}

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
}
