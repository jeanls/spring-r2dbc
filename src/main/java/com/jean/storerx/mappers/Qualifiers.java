package com.jean.storerx.mappers;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named(value = "Qualifiers")
public class Qualifiers {

    @Named(value = "toLocalDateTime")
    public LocalDateTime toLocalDateTime(final String value) {
        if (value == null) {
            return null;
        }

        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Named(value = "toStringDate")
    public String toStringDate(final LocalDateTime value) {
        if (value == null) {
            return null;
        }

        return value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
