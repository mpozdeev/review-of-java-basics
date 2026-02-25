package org.example;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LocalDateMultiCsvConverter extends AbstractBeanField<LocalDate, String> {

    private static final DateTimeFormatter FORMATTER1 =
            DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter FORMATTER2 =
            DateTimeFormatter.ofPattern("MMMM d. yyyy", Locale.ENGLISH);

    @Override
    protected LocalDate convert(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        String trimmed = value.trim();

        try {
            return LocalDate.parse(trimmed, FORMATTER1);
        } catch (DateTimeParseException ignored) {
        }

        try {
            return LocalDate.parse(trimmed, FORMATTER2);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неизвестный формат даты: " + value, e);
        }
    }
}
