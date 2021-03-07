package de.htwsaar.spotifyrecommender.spotify.model.track;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.DeserializerUtils;

import java.io.IOException;

class TrackDeserializer extends StdDeserializer<Track> {

    public TrackDeserializer() {
        super(Track.class);
    }

    @Override
    public Track deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        Track track = new Track();
        track.setId(DeserializerUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        track.setTitle(jsonNode.get("name").asText());
        track.setArtist(DeserializerUtils.extractArtists(jsonNode));
        track.setAlbum(jsonNode.get("album").get("name").asText());
        track.setImageUrl(DeserializerUtils.extractImageUrl(jsonNode.get("album")));

        return track;
    }
}
