package com.osa.spring5.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    PARTIALLY("partially");
    private final String value;
}
