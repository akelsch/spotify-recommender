package de.htwsaar.spotifyrecommender.spotify;

import de.htwsaar.spotifyrecommender.spotify.model.album.AlbumsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.artist.ArtistsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.item.Item;
import de.htwsaar.spotifyrecommender.spotify.model.item.ItemsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.item.ItemsTrackResponse;
import de.htwsaar.spotifyrecommender.spotify.model.track.TracksResponse;
import de.htwsaar.spotifyrecommender.util.CachingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
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

    public Mono<TracksResponse> getSeveralTracks(List<String> ids) {
        if (ids.isEmpty()) {
            return Mono.justOrEmpty(new TracksResponse(Collections.emptyList()));
        }

        String uri = UriComponentsBuilder.fromUriString("/v1/tracks")
                .queryParam("ids", String.join(",", ids))
                .toUriString();

        return cachingWebClient.doGet(uri, TracksResponse.class);
    }

    public Mono<AlbumsResponse> getSeveralAlbums(List<String> ids) {
        if (ids.isEmpty()) {
            return Mono.justOrEmpty(new AlbumsResponse(Collections.emptyList()));
        }

        String uri = UriComponentsBuilder.fromUriString("/v1/albums")
                .queryParam("ids", String.join(",", ids))
                .toUriString();

        return cachingWebClient.doGet(uri, AlbumsResponse.class);
    }

    public Mono<ArtistsResponse> getSeveralArtists(List<String> ids) {
        if (ids.isEmpty()) {
            return Mono.justOrEmpty(new ArtistsResponse(Collections.emptyList()));
        }

        String uri = UriComponentsBuilder.fromUriString("/v1/artists")
                .queryParam("ids", String.join(",", ids))
                .toUriString();

        return cachingWebClient.doGet(uri, ArtistsResponse.class);
    }

    public WebClient.ResponseSpec getRecentlyPlayed(MultiValueMap<String, String> queryParams) {
        return client.get()
                .uri("/v1/me/player/recently-played", uriBuilder -> uriBuilder.queryParams(queryParams).build())
                .retrieve();
    }

    public Mono<List<String>> getTopTracks(int limit, String timeRange) {
        String uri = UriComponentsBuilder.fromUriString("/v1/me/top/tracks")
                .queryParam("limit", limit)
                .queryParam("time_range", timeRange)
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsResponse.class)
                .map(ItemsResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(Item::getUri)
                .collectList();
    }

    public Mono<List<String>> getRecentlyPlayedTracks(int limit) {
        String uri = UriComponentsBuilder.fromUriString("/v1/me/player/recently-played")
                .queryParam("limit", limit)
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }

    public Mono<List<String>> getSavedTracks(int limit) {
        String uri = UriComponentsBuilder.fromUriString("/v1/me/tracks")
                .queryParam("limit", limit)
                .toUriString();

        return cachingWebClient.doGet(uri, ItemsTrackResponse.class)
                .map(ItemsTrackResponse::getItems)
                .flatMapMany(Flux::fromIterable)
                .map(item -> item.getTrack().getUri())
                .collectList();
    }
}
