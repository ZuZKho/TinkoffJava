package edu.hw5;

public class Task8 {

    private Task8() {
    }

    public static boolean isFirstPattern(String string) {
        // нечетной длины
        String pattern = "^[01]([01]{2})*$";

        return string.matches(pattern);
    }

    public static boolean isSecondPattern(String string) {
        // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
        String pattern = "^(0|1[01])([01]{2})*$";

        return string.matches(pattern);
    }

    public static boolean isThirdPattern(String string) {
        // количество 0 кратно 3
        String pattern = "^1*|(1*01*01*01*)*$";

        return string.matches(pattern);
    }

    public static boolean isFourthPattern(String string) {
        // любая строка, кроме 11 или 111
        // Not ready
        String pattern = "";

        return string.matches(pattern);
    }

    public static boolean isFifthPattern(String string) {
        // каждый нечетный символ равен 1
        String pattern = "^(1[01]{1})*1?$";

        return string.matches(pattern);
    }

    public static boolean isSixthPattern(String string) {
        // содержит не менее двух 0 и не более одной 1
        // Not ready
        String pattern = "";

        return string.matches(pattern);
    }

    public static boolean isSeventhPattern(String string) {
        // нет последовательных 1
        String pattern = "^1?(0+1)*0*$";

        return string.matches(pattern);
    }

}
