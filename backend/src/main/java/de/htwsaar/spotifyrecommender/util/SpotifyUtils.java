package de.htwsaar.spotifyrecommender.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SpotifyUtils {

    private SpotifyUtils() {
    }

    public static String extractIdFromUri(String uri) {
        return StringUtils.substringAfterLast(uri, ":");
    }

    public static String extractArtists(JsonNode node) {
        return StreamSupport.stream(node.withArray("artists").spliterator(), false)
                .map(artist -> artist.get("name").asText())
                .collect(Collectors.joining(", "));
    }

    public static String extractImageUrl(JsonNode node) {
        return StreamSupport.stream(node.withArray("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .orElse(null);
    }
}
