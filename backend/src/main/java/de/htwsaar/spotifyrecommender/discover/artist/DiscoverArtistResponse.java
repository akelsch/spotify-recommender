package de.htwsaar.spotifyrecommender.discover.artist;

import lombok.Value;

import java.util.List;

@Value
public class DiscoverArtistResponse {

    List<DiscoverArtist> artists;
}
