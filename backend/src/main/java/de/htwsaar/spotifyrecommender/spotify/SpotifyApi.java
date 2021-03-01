package de.htwsaar.spotifyrecommender.spotify;

import de.htwsaar.spotifyrecommender.spotify.model.ItemsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.ItemsTrackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SpotifyApi {

    private final WebClient client;

    @Autowired
    public SpotifyApi(WebClient oauthWebClient) {
        this.client = oauthWebClient;
    }

    public WebClient.ResponseSpec getSeveralTracks(List<String> ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("ids", String.join(",", ids));

        return client.get()
                .uri("/v1/tracks", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getSeveralAlbums(List<String> ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("ids", String.join(",", ids));

        return client.get()
                .uri("/v1/albums", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getSeveralArtists(List<String> ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("ids", String.join(",", ids));

        return client.get()
                .uri("/v1/artists", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getRecentlyPlayed(MultiValueMap<String, String> queryParams) {
        return client.get()
                .uri("/v1/me/player/recently-played", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve();
    }

    public Mono<List<String>> getTopTracks(String timeRange) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("limit", "50");
        queryParams.set("time_range", timeRange);

        return client.get()
                .uri("/v1/me/top/tracks", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve()
                .bodyToMono(ItemsResponse.class)
                .map(ItemsResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(ItemsResponse.Item::getUri)
                .collectList();
    }

    public Mono<List<String>> getRecentlyPlayedTracks() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("limit", "50");

        return client.get()
                .uri("/v1/me/player/recently-played", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve()
                .bodyToMono(ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }

    public Mono<List<String>> getSavedTracks() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("limit", "50");

        return client.get()
                .uri("/v1/me/tracks", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve()
                .bodyToMono(ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }
}
