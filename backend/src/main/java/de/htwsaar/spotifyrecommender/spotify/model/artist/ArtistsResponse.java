package de.htwsaar.spotifyrecommender.spotify.model.artist;

import lombok.Value;

import java.util.List;

@Value
public class ArtistsResponse {

    List<Artist> artists;
}
