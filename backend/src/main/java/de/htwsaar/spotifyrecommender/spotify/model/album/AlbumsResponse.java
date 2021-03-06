package de.htwsaar.spotifyrecommender.spotify.model.album;

import lombok.Value;

import java.util.List;

@Value
public class AlbumsResponse {

    List<Album> albums;
}

