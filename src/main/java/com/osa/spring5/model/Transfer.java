package com.osa.spring5.model;

import lombok.Data;

@Data
public class Transfer {

    private Time departure;
    private Time arrival;
    private Station station;
}
