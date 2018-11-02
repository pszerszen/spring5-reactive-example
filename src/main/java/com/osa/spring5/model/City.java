package com.osa.spring5.model;

import lombok.Data;

import java.util.List;

@Data
public class City {

    private Long id;
    private String name;
    private CityClass cityClass;
    private Coordinates coordinates;
    private List<Long> stations;
    private List<Long> possibleDestinations;
    private Country country;
}
