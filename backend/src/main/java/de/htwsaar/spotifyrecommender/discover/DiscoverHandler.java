package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.commons.ItemList;
import de.htwsaar.spotifyrecommender.dataset.TrackEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class DiscoverHandler {

    private final TrackEntityService trackEntityService;

    @Autowired
    public DiscoverHandler(TrackEntityService trackEntityService) {
        this.trackEntityService = trackEntityService;
    }

    public Mono<ServerResponse> discoverTracks(ServerRequest serverRequest) {
        int page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(0);
        int size = serverRequest.queryParam("size").map(Integer::parseInt).orElse(20);

        return trackEntityService.findAllTrackIds(PageRequest.of(page, size))
                .collect(Collectors.toList())
                .flatMap(trackIds -> ServerResponse.ok().bodyValue(new ItemList<>(trackIds)));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest serverRequest) {
        return null;
    }
}
