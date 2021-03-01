package de.htwsaar.spotifyrecommender.spotify;

import de.htwsaar.spotifyrecommender.spotify.model.ItemsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.ItemsTrackResponse;
import de.htwsaar.spotifyrecommender.util.CachingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SpotifyApi {

    private final WebClient client;
    private final CachingWebClient cachingWebClient;

    @Autowired
    public SpotifyApi(WebClient oauthWebClient, CachingWebClient cachingWebClient) {
        this.client = oauthWebClient;
        this.cachingWebClient = cachingWebClient;
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
        String uri = UriComponentsBuilder.fromUriString("/v1/me/top/tracks")
                .queryParam("limit", "50")
                .queryParam("time_range", timeRange)
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsResponse.class)
                .map(ItemsResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(ItemsResponse.Item::getUri)
                .collectList();
    }

    public Mono<List<String>> getRecentlyPlayedTracks() {
        String uri = UriComponentsBuilder.fromUriString("/v1/me/player/recently-played")
                .queryParam("limit", "50")
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }

    public Mono<List<String>> getSavedTracks() {
        String uri = UriComponentsBuilder.fromUriString("/v1/me/tracks")
                .queryParam("limit", "50")
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }
}
