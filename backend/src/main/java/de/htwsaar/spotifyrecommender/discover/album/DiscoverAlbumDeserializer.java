package de.htwsaar.spotifyrecommender.discover.album;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.SpotifyUtils;

import java.io.IOException;

class DiscoverAlbumDeserializer extends StdDeserializer<DiscoverAlbum> {

    public DiscoverAlbumDeserializer() {
        super(DiscoverAlbum.class);
    }

    @Override
    public DiscoverAlbum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverAlbum album = new DiscoverAlbum();
        album.setId(SpotifyUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        album.setName(jsonNode.get("name").asText());
        album.setArtist(SpotifyUtils.extractArtists(jsonNode));
        album.setImageUrl(SpotifyUtils.extractImageUrl(jsonNode));

        return album;
    }
}
