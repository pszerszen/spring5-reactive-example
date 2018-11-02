package com.osa.spring5.controller;

import com.osa.spring5.model.Trip;
import com.osa.spring5.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TripController {

    private final TripService service;

    @GetMapping("/find/{number}")
    public Flux<Trip> findTrips(@PathVariable int number) {
        return service.findTrips(null, number);
    }
}
