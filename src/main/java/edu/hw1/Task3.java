package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Task3 {

    private Task3() {}


    static public boolean isNestable(Integer[] firstArr, Integer[] secondArr) {
        ArrayList<Integer> firstList = new ArrayList<Integer>(Arrays.asList(firstArr));
        ArrayList<Integer> secondList = new ArrayList<Integer>(Arrays.asList(secondArr));

        int firstMin = Collections.min(firstList);
        int firstMax = Collections.max(firstList);

        int secondMin = Collections.min(secondList);
        int secondMax = Collections.max(secondList);

        return firstMin > secondMin && firstMax < secondMax;
    }
}
