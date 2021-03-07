package de.htwsaar.spotifyrecommender.spotify.model.album;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.DeserializerUtils;

import java.io.IOException;

class AlbumDeserializer extends StdDeserializer<Album> {

    public AlbumDeserializer() {
        super(Album.class);
    }

    @Override
    public Album deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        Album album = new Album();
        album.setId(DeserializerUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        album.setName(jsonNode.get("name").asText());
        album.setArtist(DeserializerUtils.extractArtists(jsonNode));
        album.setImageUrl(DeserializerUtils.extractImageUrl(jsonNode));

        return album;
    }
}
