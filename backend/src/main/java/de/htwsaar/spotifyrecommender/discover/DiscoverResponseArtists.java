package de.htwsaar.spotifyrecommender.discover;

import lombok.Value;

import java.util.List;

@Value
class DiscoverResponseArtists {

    List<DiscoverArtist> artists;
}
