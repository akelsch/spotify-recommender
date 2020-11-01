package de.htwsaar.spotifyrecommender.song;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SongHandler {

    private final SongRepository songRepository;

    public Mono<ServerResponse> query(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(songRepository.findAll(), Song.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Song.class)
                .map(song -> {
                    song.setId(null);
                    return song;
                })
                .flatMap(songRepository::save)
                .flatMap(song -> ServerResponse.created(request.uriBuilder().pathSegment(song.getId()).build())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(song)
                );
    }

    public Mono<ServerResponse> read(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(songRepository.findById(id), Song.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(Song.class)
                .map(song -> {
                    song.setId(id);
                    return song;
                })
                .flatMap(songRepository::save)
                .flatMap(song -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(song)
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return songRepository.deleteById(id)
                .then(ServerResponse.noContent().build());
    }
}
