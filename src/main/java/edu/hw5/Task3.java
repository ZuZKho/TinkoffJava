package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private Task3() {
    }

    static private DateProcessor getChain() {
        DateProcessor processor = new AgoProcessor(null);
        processor = new DashProcessor(processor);
        processor = new SlashProcessor(processor);
        return new DayProcessor(processor);
    }

    static public Optional<LocalDate> parseDate(String string) {
        DateProcessor processor = getChain();
        LocalDate result = processor.parse(string);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    abstract static class DateProcessor {
        public DateProcessor nextProcessor;

        DateProcessor(DateProcessor nextProcessor) {
            this.nextProcessor = nextProcessor;
        }

        public abstract LocalDate parse(String string);
    }

    static class AgoProcessor extends DateProcessor {
        AgoProcessor(DateProcessor nextProcessor) {
            super(nextProcessor);
        }

        @Override
        public LocalDate parse(String string) {
            if (string.equals("1 day ago")) {
                return LocalDate.now().minusDays(1);
            }

            Pattern pattern = Pattern.compile("^(\\d+) days ago$");
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                int days = Integer.parseInt(matcher.group(1));
                if (days != 1) {
                    return LocalDate.now().minusDays(days);
                }
            }

            return nextProcessor == null ? null : nextProcessor.parse(string);
        }
    }

    static class DayProcessor extends DateProcessor {
        DayProcessor(DateProcessor nextProcessor) {
            super(nextProcessor);
        }

        @Override
        public LocalDate parse(String string) {
            return switch (string) {
                case "today" -> LocalDate.now();
                case "yesterday" -> LocalDate.now().minusDays(1);
                case "tomorrow" -> LocalDate.now().plusDays(1);
                default -> {
                    if (nextProcessor != null) {
                        yield nextProcessor.parse(string);
                    }
                    yield null;
                }
            };
        }
    }

    @SuppressWarnings("MagicNumber")
    static class DashProcessor extends DateProcessor {
        DashProcessor(DateProcessor nextProcessor) {
            super(nextProcessor);
        }

        @Override
        public LocalDate parse(String string) {
            Pattern pattern = Pattern.compile("^(\\d{1,4})-(\\d{1,2})-(\\d{1,2})$");

            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                int year = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int day = Integer.parseInt(matcher.group(3));

                try {
                    return LocalDate.of(year, month, day);
                } catch (Exception ignored) {
                    return nextProcessor == null ? null : nextProcessor.parse(string);
                }
            }

            return nextProcessor == null ? null : nextProcessor.parse(string);
        }
    }

    @SuppressWarnings("MagicNumber")
    static class SlashProcessor extends DateProcessor {
        SlashProcessor(DateProcessor nextProcessor) {
            super(nextProcessor);
        }

        @Override
        public LocalDate parse(String string) {
            Pattern pattern = Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{1,4})$");

            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int year = Integer.parseInt(matcher.group(3));

                try {
                    return LocalDate.of(year, month, day);
                } catch (Exception ignored) {
                    return nextProcessor == null ? null : nextProcessor.parse(string);
                }
            }

            return nextProcessor == null ? null : nextProcessor.parse(string);
        }
    }

}
