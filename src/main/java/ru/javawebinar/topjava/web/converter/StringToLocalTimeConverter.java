package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

    private String timePattern = "HH:mm";

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    @Override
    public LocalTime convert(String timeString) {
        return LocalTime.parse(timeString, DateTimeFormatter.ofPattern(timePattern));
    }
}
