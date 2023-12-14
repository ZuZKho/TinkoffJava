package edu.hw3;

import java.util.ArrayList;

public class Task2 {

    private Task2() {
    }

    public static String[] clusterize(String text) {
        ArrayList<String> answer = new ArrayList<>();
        int stackSize = 0;
        int begin = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                stackSize++;
            } else {
                stackSize--;
                if (stackSize == 0) {
                    answer.add(text.substring(begin, i + 1));
                    begin = i + 1;
                }
            }
        }
        return answer.toArray(new String[0]);
    }

}
