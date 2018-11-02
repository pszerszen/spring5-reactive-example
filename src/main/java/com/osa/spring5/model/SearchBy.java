package com.osa.spring5.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SearchBy {

    CITIES("cities"),
    STATIONS("stations"),
    MIXED("mixed");

    private final String value;

}
