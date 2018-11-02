package com.osa.spring5.controller;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
class NetworkControllerTest {

    private WebClient client;

    @BeforeEach
    void setUp() {
        client = WebClient.create("http://localhost:8080");
    }

    @Test
    void getStations() {
        Flux<Station> flux = client.get()
                .uri("network/stations/{number}", 1000)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Station.class);
        flux.subscribe(trip -> log.info("Got station: {}", trip));
        flux.toStream().collect(Collectors.toList());
    }

    @Test
    void getNetwork() {
        Mono<Network> mono = client.get()
                .uri("/network")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Network.class);
        mono.subscribe(network -> log.info("Got network: {}", network));
        mono.block();
    }
}
