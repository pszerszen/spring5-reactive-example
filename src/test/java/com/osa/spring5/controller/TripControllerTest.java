package com.osa.spring5.controller;

import com.osa.spring5.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TripControllerTest {

    private WebClient client;

    @BeforeEach
    void setUp() {
        client = WebClient.create("http://localhost:8080");
    }

    @Test
    void findTrips() {
        client.get()
                .uri("trips/find/{number}", 100)
                .retrieve()
                .bodyToFlux(Trip.class)
                .subscribe(trip -> log.error("Got trip: {}", trip));
    }
}
