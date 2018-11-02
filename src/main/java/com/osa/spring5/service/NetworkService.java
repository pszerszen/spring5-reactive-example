package com.osa.spring5.service;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NetworkService {

    private final PodamFactory podamFactory;

    public Mono<Network> getNetwork() {
        return Mono.fromFuture(supplyAsync(this::prepareNetwork));
    }

    private Network prepareNetwork() {
        log.info("Getting network");
        return podamFactory.manufacturePojo(Network.class);
    }

    public Flux<Station> getStations(int rangeClose) {
        return Flux.fromStream(IntStream.range(0, rangeClose)
                .boxed().parallel()
                .map(this::prepareStation));
    }

    private Station prepareStation(int i) {
        log.info("Station nr {}", i);
        return podamFactory.manufacturePojo(Station.class);
    }
}
