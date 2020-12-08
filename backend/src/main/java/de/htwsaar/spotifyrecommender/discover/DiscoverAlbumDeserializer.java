package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DiscoverAlbumDeserializer extends StdDeserializer<DiscoverAlbum> {

    public DiscoverAlbumDeserializer() {
        super(DiscoverTrackDeserializer.class);
    }

    @Override
    public DiscoverAlbum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverAlbum album = new DiscoverAlbum();
        album.setId(StringUtils.substringAfterLast(jsonNode.get("uri").asText(), ":"));
        album.setName(jsonNode.get("name").asText());
        album.setArtist(extractArtists(jsonNode));
        album.setImageUrl(extractAlbumImageUrl(jsonNode));

        return album;
    }

    private static String extractArtists(JsonNode album) {
        return StreamSupport.stream(album.withArray("artists").spliterator(), false)
                .map(artist -> artist.get("name").asText())
                .collect(Collectors.joining(", "));
    }

    private static String extractAlbumImageUrl(JsonNode album) {
        return StreamSupport.stream(album.get("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .get();
    }
}
