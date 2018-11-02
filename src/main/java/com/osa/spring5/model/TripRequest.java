package com.osa.spring5.model;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class TripRequest {
    private static final long serialVersionUID = -8301402847341826707L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private SearchBy searchBy;
    private String fromCityId;
    private String toCityId;
    private String fromStationId;
    private String toStationId;
    private int adult;
    private int children;
    private boolean bikes;
    private Currency currency;
    private String departureDate;

}
