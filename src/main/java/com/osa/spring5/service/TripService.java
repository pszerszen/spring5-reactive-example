package com.osa.spring5.service;

import com.osa.spring5.model.Trip;
import com.osa.spring5.model.TripRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TripService {

    private final PodamFactory podamFactory;

    public Flux<Trip> findTrips(TripRequest request, int expectedNumber) {
        return Flux.fromStream(IntStream.range(0, expectedNumber).boxed()
                .map(i -> podamFactory.manufacturePojo(Trip.class)));
    }
}