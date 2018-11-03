package com.osa.spring5.controller;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import com.osa.spring5.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static reactor.core.scheduler.Schedulers.parallel;

@Slf4j
class ReactiveConnectionTest {

    private WebClient client;
    private AtomicBoolean finished;

    @BeforeEach
    void setUp() {
        client = WebClient.create("http://localhost:8080");
        finished = new AtomicBoolean(false);
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        while (!finished.get()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    private void errorHandler(Throwable error) {
        log.error("Error", error);
    }

    private void handleFinished() {
        finished.set(true);
    }

    @Nested
    class TripControllerTest {
        @Test
        void findTrips() {
            client.get()
                    .uri("/trips/{number}", 1000)
                    .accept(APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToFlux(Trip.class)
                    .doOnNext(trip -> log.info("Got trip: {}", trip))
                    .doOnError(ReactiveConnectionTest.this::errorHandler)
                    .doOnComplete(ReactiveConnectionTest.this::handleFinished)
                    .subscribeOn(parallel())
                    .subscribe();
        }
    }

    @Nested
    class NetworkControllerTest {

        @Test
        void getStations() {
            client.get()
                    .uri("/network/stations/{number}", 1000)
                    .accept(APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToFlux(Station.class)
                    .doOnNext(station -> log.info("Got station: {}", station))
                    .doOnError(ReactiveConnectionTest.this::errorHandler)
                    .doOnComplete(ReactiveConnectionTest.this::handleFinished)
                    .subscribeOn(parallel())
                    .subscribe();
        }

        @Test
        void getNetwork() {
            client.get()
                    .uri("/network")
                    .accept(APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToMono(Network.class)
                    .doOnNext(network -> log.info("Got network: {}", network))
                    .doOnError(ReactiveConnectionTest.this::errorHandler)
                    .doAfterSuccessOrError((network, throwable) -> handleFinished())
                    .subscribeOn(parallel())
                    .subscribe();
        }
    }
}
