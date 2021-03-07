package de.htwsaar.spotifyrecommender.spotify.model.artist;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.DeserializerUtils;

import java.io.IOException;

class ArtistDeserializer extends StdDeserializer<Artist> {

    public ArtistDeserializer() {
        super(Artist.class);
    }

    @Override
    public Artist deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        Artist artist = new Artist();
        artist.setId(DeserializerUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        artist.setName(jsonNode.get("name").asText());
        artist.setImageUrl(DeserializerUtils.extractImageUrl(jsonNode));

        return artist;
    }
}
