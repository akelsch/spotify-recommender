package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class DiscoverTrackDeserializer extends StdDeserializer<DiscoverTrack> {

    public DiscoverTrackDeserializer() {
        super(DiscoverTrackDeserializer.class);
    }

    @Override
    public DiscoverTrack deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverTrack item = new DiscoverTrack();
        item.setId(StringUtils.substringAfterLast(jsonNode.get("uri").asText(), ":"));
        item.setTitle(jsonNode.get("name").asText());
        item.setArtist(extractArtists(jsonNode));
        item.setAlbum(jsonNode.get("album").get("name").asText());
        item.setImageUrl(extractAlbumImageUrl(jsonNode));

        return item;
    }

    private static String extractArtists(JsonNode track) {
        return StreamSupport.stream(track.withArray("artists").spliterator(), false)
                .map(artist -> artist.get("name").asText())
                .collect(Collectors.joining(", "));
    }

    private static String extractAlbumImageUrl(JsonNode track) {
        return StreamSupport.stream(track.get("album").withArray("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .get();
    }
}
