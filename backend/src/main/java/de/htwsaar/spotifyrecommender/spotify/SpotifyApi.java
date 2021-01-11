package de.htwsaar.spotifyrecommender.spotify;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

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
        queryParams.put("ids", List.of(Strings.join(ids, ',')));
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/tracks").queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getSeveralAlbums(List<String> ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.put("ids", List.of(Strings.join(ids, ',')));
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/albums").queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getSeveralArtists(List<String> ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.put("ids", List.of(Strings.join(ids, ',')));
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/artists").queryParams(queryParams).build())
                .retrieve();
    }

    public WebClient.ResponseSpec getRecentlyPlayedTracks(MultiValueMap<String, String> queryParams) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/me/player/recently-played").queryParams(queryParams).build())
                .retrieve();
    }
}
