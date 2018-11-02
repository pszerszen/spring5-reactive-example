package com.osa.spring5.model;

import lombok.Data;

import java.util.List;

@Data
public class Network {

    private List<City> cities;
    private List<Station> stations;

}
