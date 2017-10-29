package com.inno.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DefaultDateConverter implements DateConverter{
    private static final String RU_PATTERN = "dd.MM.yyyy";

    @Override
    public String formatLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RU_PATTERN);
        return localDate.format(formatter);
    }

    @Override
    public LocalDate parseStringToLocalDate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(RU_PATTERN);

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
