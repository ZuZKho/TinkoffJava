package edu.hw5;

public class Task7 {

    private Task7() {
    }

    public static boolean isFirstPattern(String string) {
        // содержит не менее 3 символов, причем третий символ равен 0
        String pattern = "^[01]{2}0.*$";

        return string.matches(pattern);
    }

    public static boolean isSecondPattern(String string) {
        // начинается и заканчивается одним и тем же символом
        String pattern = "^|1|0|0.*0$|^1.*1$";

        return string.matches(pattern);
    }

    public static boolean isThirdPattern(String string) {
        // длина не менее 1 и не более 3
        String pattern = "^[01]{1,3}$";

        return string.matches(pattern);
    }

}
