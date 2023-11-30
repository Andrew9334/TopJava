package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final LocalDateTime MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0);
    private static final LocalDateTime MAX_DATE = LocalDateTime.of(3000, 1, 1, 0, 0);

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T object, T start, T end) {
        return (start == null || object.compareTo(start) >= 0) && (end == null || object.compareTo(end) <= 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

