package com.osa.spring5.controller;

import com.osa.spring5.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static reactor.core.scheduler.Schedulers.parallel;

@Slf4j
class TripControllerTest {

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

    @Test
    void findTrips() {
        client.get()
                .uri("/trips/{number}", 1000)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Trip.class)
                .doOnNext(trip -> log.info("Got trip: {}", trip))
                .doOnError(error -> log.error("Error", error))
                .doOnComplete(() -> finished.set(true))
                .subscribeOn(parallel())
                .subscribe();
    }
}
