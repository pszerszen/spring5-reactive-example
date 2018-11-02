package com.osa.spring5.service;

import com.osa.spring5.model.Network;
import com.osa.spring5.model.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NetworkService {

    private final PodamFactory podamFactory;

    public Mono<Network> getNetwork() {
        return Mono.fromFuture(supplyAsync(() -> podamFactory.manufacturePojo(Network.class)));
    }

    public Flux<Station> getStations(int rangeClose) {
        return Flux.fromStream(IntStream.range(0, rangeClose).boxed()
                .map(i -> podamFactory.manufacturePojo(Station.class)));
    }
}
