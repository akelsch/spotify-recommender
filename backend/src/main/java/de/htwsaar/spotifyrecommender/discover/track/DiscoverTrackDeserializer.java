package de.htwsaar.spotifyrecommender.discover.track;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.SpotifyUtils;

import java.io.IOException;

class DiscoverTrackDeserializer extends StdDeserializer<DiscoverTrack> {

    public DiscoverTrackDeserializer() {
        super(DiscoverTrack.class);
    }

    @Override
    public DiscoverTrack deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        DiscoverTrack track = new DiscoverTrack();
        track.setId(SpotifyUtils.extractIdFromUri(jsonNode.get("uri").asText()));
        track.setTitle(jsonNode.get("name").asText());
        track.setArtist(SpotifyUtils.extractArtists(jsonNode));
        track.setAlbum(jsonNode.get("album").get("name").asText());
        track.setImageUrl(SpotifyUtils.extractImageUrl(jsonNode.get("album")));

        return track;
    }
}
