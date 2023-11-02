package edu.hw3;

public class Task1 {

    private Task1() {
    }

    public static String atbash(String text) {
        char[] charArray = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                charArray[i] = (char) ('Z' - (text.charAt(i) - 'A'));
            } else if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                charArray[i] = (char) ('z' - (text.charAt(i) - 'a'));
            } else {
                charArray[i] = text.charAt(i);
            }
        }
        return new String(charArray);
    }

}
