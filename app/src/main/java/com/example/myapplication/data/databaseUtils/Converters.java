package com.example.myapplication.data.databaseUtils;

import androidx.room.TypeConverter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static Instant fromTimestamp(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Instant date) {
        return date == null ? null : date.toEpochMilli();
    }

    public static String formatDate(Long millis) {
        if (millis == null) {
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }
}
