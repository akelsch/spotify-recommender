package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class SpotifyItemDeserializer extends StdDeserializer<Track> {

    public SpotifyItemDeserializer() {
        super(Track.class);
    }

    @Override
    public Track deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        JsonNode track = jsonNode.get("track");
        JsonNode playedAt = jsonNode.get("played_at");

        Track item = new Track();
        item.setId(StringUtils.substringAfterLast(track.get("uri").asText(), ":"));
        item.setTitle(track.get("name").asText());
        item.setArtist(extractArtists(track));
        item.setAlbum(track.get("album").get("name").asText());
        item.setImageUrl(extractAlbumImageUrl(track));
        item.setPlayedAt(playedAt.asText());

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
