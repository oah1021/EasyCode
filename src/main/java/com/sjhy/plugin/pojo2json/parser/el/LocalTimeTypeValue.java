package com.sjhy.plugin.pojo2json.parser.el;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalTimeTypeValue extends TemporalTypeValue {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Object getRandomValue() {
        return LocalTime
                .ofInstant(Instant.ofEpochMilli((long) super.getRandomValue()), ZoneId.systemDefault())
                .format(formatter);
    }

    @Override
    public Object getValue() {
        return LocalTime
                .ofInstant(Instant.ofEpochMilli((long) super.getValue()), ZoneId.systemDefault())
                .format(formatter);
    }
}
