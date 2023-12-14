package edu.hw3;

import java.util.Comparator;

public class Task7Comparator implements Comparator<String> {

    @Override
    public int compare(String a, String b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return a.compareTo(b);
    }

}
