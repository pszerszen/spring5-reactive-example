package com.osa.spring5.model;

import lombok.Data;

import java.util.List;

@Data
public class Trip {
    private static final long serialVersionUID = 7543478626620070263L;

    private Station from;
    private Station to;
    private List<TripItem> items;

}
