package com.osa.spring5.controller;

import com.osa.spring5.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Slf4j
class TripControllerTest {

    private WebClient client;

    @BeforeEach
    void setUp() {
        client = WebClient.create("http://localhost:8080");
    }

    @Test
    void findTrips() {
        Flux<Trip> flux = client.get()
                .uri("trips/find/{number}", 100)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Trip.class);
        flux.subscribe(trip -> log.info("Got trip: {}", trip));
        flux.toStream(10).collect(Collectors.toList());
    }
}
