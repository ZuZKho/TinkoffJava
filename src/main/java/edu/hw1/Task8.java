package edu.hw1;

class Task8 {

    static final int SIDELEN = 8;
    static final int STEP2 = 2;
    static final int STEP1 = 1;
    static int[] xes = new int[] {STEP1, -STEP1, STEP2, -STEP2, STEP1, -STEP1, STEP2, -STEP2};
    static int[] yes = new int[] {STEP2, STEP2, STEP1, STEP1, -STEP2, -STEP2, -STEP1, -STEP1};

    private Task8() {}

    static private boolean knightsUnderAttack(int i, int j, int[][] arr) {
        for (int k = 0; k < SIDELEN; k++) {
            if (arr[i][j] == 1
                && i + xes[k] < SIDELEN && i + xes[k] >= 0
                && j + yes[k] < SIDELEN && j + yes[k] >= 0) {

                if (arr[i + xes[k]][j + yes[k]] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    static public boolean knightBoardCapture(int[][] arr) {

        for (int i = 0; i < SIDELEN; i++) {
            for (int j = 0; j < SIDELEN; j++) {
                if (arr[i][j] == 1 && knightsUnderAttack(i, j, arr)) {
                    return false;
                }
            }
        }

        return true;
    }

}
