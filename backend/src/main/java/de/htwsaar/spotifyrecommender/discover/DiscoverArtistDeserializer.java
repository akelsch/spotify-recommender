package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class DiscoverArtistDeserializer extends StdDeserializer<DiscoverArtist> {

    public DiscoverArtistDeserializer() {
        super(DiscoverTrackDeserializer.class);
    }

    @Override
    public DiscoverArtist deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverArtist artist = new DiscoverArtist();
        artist.setId(StringUtils.substringAfterLast(jsonNode.get("uri").asText(), ":"));
        artist.setName(jsonNode.get("name").asText());
        artist.setImageUrl(extractAlbumImageUrl(jsonNode));

        return artist;
    }

    private static String extractAlbumImageUrl(JsonNode artist) {
        return StreamSupport.stream(artist.get("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .get();
    }
}
