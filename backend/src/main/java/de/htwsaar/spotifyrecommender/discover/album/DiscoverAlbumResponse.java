package de.htwsaar.spotifyrecommender.discover.album;

import lombok.Value;

import java.util.List;

@Value
public class DiscoverAlbumResponse {

    List<DiscoverAlbum> albums;
}

