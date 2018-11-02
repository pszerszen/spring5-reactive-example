package com.osa.spring5.controller;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import com.osa.spring5.service.NetworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/network")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NetworkController {

    private final NetworkService service;

    @GetMapping("/stations/{number}")
    public Flux<Station> getStations(@PathVariable int number) {
        return service.getStations(number);
    }

    @GetMapping
    public Mono<Network> get() {
        return service.getNetwork();
    }
}
