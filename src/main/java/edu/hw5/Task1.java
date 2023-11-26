package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;

public class Task1 {

    private Task1() {
    }

    public static Duration getDuration(List<String> log) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        OptionalDouble averageTime = log.stream().map(customerLog -> {
            String[] times = customerLog.split(" - ");
            LocalDateTime startDate = LocalDateTime.parse(times[0], formatter);
            LocalDateTime endDate = LocalDateTime.parse(times[1], formatter);
            return Duration.between(startDate, endDate).toMillis();
        }).mapToLong(i -> i).average();

        if (averageTime.isPresent()) {
            return Duration.ofMillis(Double.valueOf(averageTime.getAsDouble()).longValue());
        }
        return null;
    }

}
