package com.osa.spring5.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class TripItem implements Comparable<TripItem> {

    private Long id;
    private Time departure;
    private Time arrival;
    private double price;
    private List<Transfer> transfers;
    private Status status;
    private List<Link> links;

    @Override
    public int compareTo(final TripItem o) {
        return Objects.compare(departure, o.getDeparture(), Time::compareTo);
    }
}
