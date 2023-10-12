package edu.hw1;

class Task1 {

    // Чтобы не ругался checkstyle
    private Task1() {}

    static public int minutesToSeconds(String time) {
        String[] parts = time.split(":");

        if (parts.length > 2) {
            return -1;
        }
        int mm;
        int ss;

        try {
            mm = Integer.parseInt(parts[0]);
            ss = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        final int secondsInMinute = 60;
        if (ss < 0 || mm < 0 || ss >= secondsInMinute) {
            return -1;
        }

        return mm * secondsInMinute + ss;
    }
}
