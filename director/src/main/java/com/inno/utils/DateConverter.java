package com.inno.utils;

import java.time.LocalDate;

public interface DateConverter {
    String formatLocalDateToString(LocalDate localDate);
    LocalDate parseStringToLocalDate(String dateStr);
}
