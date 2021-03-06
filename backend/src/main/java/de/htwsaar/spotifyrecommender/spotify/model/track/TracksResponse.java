package de.htwsaar.spotifyrecommender.spotify.model.track;

import lombok.Value;

import java.util.List;

@Value
public class TracksResponse {

    List<Track> tracks;
}
