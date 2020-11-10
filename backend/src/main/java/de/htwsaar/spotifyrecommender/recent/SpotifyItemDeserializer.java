package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class SpotifyItemDeserializer extends StdDeserializer<RecentlyPlayedItem> {

    public SpotifyItemDeserializer() {
        super(RecentlyPlayedItem.class);
    }

    @Override
    public RecentlyPlayedItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        JsonNode track = jsonNode.get("track");
        JsonNode context = jsonNode.get("context");

        if (context.isNull()) {
            return handleTrackWithoutContext(track);
        }

        return handleTrackWithContext(track, context);
    }

    private RecentlyPlayedItem handleTrackWithoutContext(JsonNode track) {
        RecentlyPlayedItem item = new RecentlyPlayedItem();

        item.setId(extractIdFromUri(track));
        item.setName(track.get("name").asText());
        item.setImageUrl(extractAlbumImageUrl(track));
        item.setType(track.get("type").asText()); // track

        return item;
    }

    private RecentlyPlayedItem handleTrackWithContext(JsonNode track, JsonNode context) {
        RecentlyPlayedItem item = new RecentlyPlayedItem();

        String type = context.get("type").asText();
        item.setType(type);

        switch (type) {
            case "album" -> {
                item.setId(extractIdFromUri(context));
                item.setName(track.get("album").get("name").asText());
                item.setImageUrl(extractAlbumImageUrl(track));
            }
            case "artist" -> {
                JsonNode artist = extractArtistFromContext(track, context);
                item.setId(artist.get("id").asText());
                item.setName(artist.get("name").asText());
                // TODO image missing
            }
            case "playlist" -> {
                item.setId(extractIdFromUri(context));
                // TODO name missing
                // TODO image missing
            }
        }

        return item;
    }

    private static String extractIdFromUri(JsonNode node) {
        return StringUtils.substringAfterLast(node.get("uri").asText(), ":");
    }

    private static String extractAlbumImageUrl(JsonNode track) {
        return StreamSupport.stream(track.get("album").withArray("images").spliterator(), false)
                .skip(1) // skip high-resolution image
                .findFirst()
                .map(image -> image.get("url").asText())
                .get();
    }

    private static JsonNode extractArtistFromContext(JsonNode track, JsonNode context) {
        String artistUriFromContext = context.get("uri").asText();
        return StreamSupport.stream(track.withArray("artists").spliterator(), false)
                .filter(artist -> artist.get("uri").asText().equals(artistUriFromContext))
                .findFirst()
                .get();
    }
}
