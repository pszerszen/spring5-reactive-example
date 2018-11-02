package com.osa.spring5.controller;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import com.osa.spring5.service.NetworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/network")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NetworkController {

    private final NetworkService service;

    @GetMapping("/stations/{number}")
    public Flux<Station> getStations(@PathVariable int number) {
        log.info("Calling for {} stations.", number);
        try {
            return service.getStations(number);
        } finally {
            log.info("Call handled.");
        }
    }

    @GetMapping
    public Mono<Network> get() {
        log.info("Calling for network");
        try {
            return service.getNetwork();
        } finally {
            log.info("Call handled");
        }
    }
}
