package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {

    private Task2() {
    }

    public static List<LocalDate> getBadFridaysOfYear(int year) {
        List<LocalDate> badFridays = new ArrayList<>();
        LocalDate current = LocalDate.of(year, 1, 13);

        while (current.getYear() == year) {
            if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
                badFridays.add(current);
            }
            current = current.plusMonths(1);
        }

        return badFridays;
    }

    public static LocalDate getNextBadFriday(LocalDate localDate) {
        return localDate.with(nextBadFriday);
    }

    private static TemporalAdjuster nextBadFriday = TemporalAdjusters.ofDateAdjuster(inDate -> {
        var date = inDate;
        if (date.getDayOfMonth() > 13) {
            date = date.with(TemporalAdjusters.firstDayOfNextMonth());
            date = date.plusDays(12);
        } else {
            date = date.withDayOfMonth(13);
        }

        while (date.getDayOfWeek() != DayOfWeek.FRIDAY) {
            date = date.plusMonths(1);
        }
        return date;
    });

}
