package com.osa.spring5.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CityClass {
    A(0.5),
    B(0.3),
    C(0.2);

    private final double probability;

}
