package com.osa.spring5.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Time implements Comparable<Time> {

    private long timestamp;
    private String timezone;

    @Override
    public int compareTo(final Time o) {
        return Objects.compare(timestamp, o.getTimestamp(), Long::compareTo);
    }
}
