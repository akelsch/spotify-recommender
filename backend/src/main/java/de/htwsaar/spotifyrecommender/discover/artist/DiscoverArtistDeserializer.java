package de.htwsaar.spotifyrecommender.discover.artist;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.SpotifyUtils;

import java.io.IOException;

class DiscoverArtistDeserializer extends StdDeserializer<DiscoverArtist> {

    public DiscoverArtistDeserializer() {
        super(DiscoverArtist.class);
    }

    @Override
    public DiscoverArtist deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverArtist artist = new DiscoverArtist();
        artist.setId(SpotifyUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        artist.setName(jsonNode.get("name").asText());
        artist.setImageUrl(SpotifyUtils.extractImageUrl(jsonNode));

        return artist;
    }
}
