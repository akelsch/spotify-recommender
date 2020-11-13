package de.htwsaar.spotifyrecommender.recent;

import lombok.Data;

import java.util.List;

@Data
class Response {

    private List<Track> items;
}
