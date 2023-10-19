package edu.hw1;

class Task4 {

    private Task4() {}

    static public String fixString(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i + 1 < strChar.length; i += 2) {
            char tmp = strChar[i];
            strChar[i] = strChar[i + 1];
            strChar[i + 1] = tmp;
        }
        return new String(strChar);
    }
}
