package edu.hw5;

public class Task5 {

    private Task5() {
    }

    public static boolean isCarSign(String sign) {
        return sign.matches("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$");
    }
}
