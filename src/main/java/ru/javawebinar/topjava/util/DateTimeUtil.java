package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T object, T start, T end) {
        return (start == null || object.compareTo(start) >= 0) && (end == null || object.compareTo(end) <= 0);
    }

    public static LocalDate parseToLocalDate(String string) {
        return string == null || string.isEmpty() ? null : LocalDate.parse(string, DATE_FORMATTER);
    }

    public static LocalTime parseToLocalTime(String string) {
        return string== null || string.isEmpty() ? null : LocalTime.parse(string, TIME_FORMATTER);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_FORMATTER);
    }
}

