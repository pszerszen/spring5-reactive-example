package com.osa.spring5.model;

import lombok.Data;

@Data
public class Station {

    private Long id;
    private String code;
    private String name;
    private String address;
    private Coordinates coordinates;
    private City city;
    private Country country;
}
