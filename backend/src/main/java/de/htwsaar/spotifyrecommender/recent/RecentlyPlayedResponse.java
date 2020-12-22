package de.htwsaar.spotifyrecommender.recent;

import lombok.Value;

import java.util.List;

@Value
class RecentlyPlayedResponse {

    List<RecentlyPlayedTrack> items;

    RecentlyPlayedCursors cursors;
}
