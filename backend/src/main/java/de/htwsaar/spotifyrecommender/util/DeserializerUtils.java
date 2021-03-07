package de.htwsaar.spotifyrecommender.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Utility class providing functionality that is commonly used
 * when deserializing JSON responses from the Spotify API.
 */
public final class DeserializerUtils {

    private DeserializerUtils() {
    }

    /**
     * Extracts the ID of a Spotify item from its URI.
     *
     * @param uri the complete URI, e.g. "spotify:track:4uLU6hMCjMI75M1A2tKUQC"
     * @return the extracted ID, e.g. "4uLU6hMCjMI75M1A2tKUQC"
     */
    public static String extractIdFromUri(String uri) {
        return StringUtils.substringAfterLast(uri, ":");
    }

    /**
     * Joins an array of artists to a single string, separated by comma.
     *
     * @param node a JSON node containing an "artists" array
     * @return the extracted artists string
     */
    public static String extractArtists(JsonNode node) {
        return StreamSupport.stream(node.withArray("artists").spliterator(), false)
                .map(artist -> artist.get("name").asText())
                .collect(Collectors.joining(", "));
    }

    /**
     * Selects a cover image from an array of multiple images.
     *
     * @param node a JSON node containing an "images" array
     * @return the extracted image URL or {@code null}
     */
    public static String extractImageUrl(JsonNode node) {
        return StreamSupport.stream(node.withArray("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .orElse(null);
    }
}
