package edu.hw5;

public class Task6 {

    private Task6() {
    }

    public static boolean substring(String text, String string) {
        String pattern = ".*";
        for (int i = 0; i < string.length(); i++) {
            pattern = pattern.concat(string.charAt(i) + ".*");
        }

        return text.matches(pattern);
    }

}
