package de.htwsaar.spotifyrecommender.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RatingType {
    TRACK("track"), ARTIST("artist"), ALBUM("album");

    private final String value;
}
