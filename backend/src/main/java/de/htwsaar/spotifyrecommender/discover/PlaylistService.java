package de.htwsaar.spotifyrecommender.discover;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Flux<Playlist> insertAll(Iterable<Playlist> playlists) {
        return Flux.fromIterable(playlists).concatMap(r2dbcEntityTemplate::insert);
    }
}
