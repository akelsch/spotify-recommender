package de.htwsaar.spotifyrecommender.spotify.model.recent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.htwsaar.spotifyrecommender.util.SpotifyUtils;

import java.io.IOException;

class ItemDeserializer extends StdDeserializer<Item> {

    public ItemDeserializer() {
        super(Item.class);
    }

    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();

        JsonNode track = jsonNode.get("track");
        JsonNode playedAt = jsonNode.get("played_at");

        Item item = new Item();
        item.setId(SpotifyUtils.extractIdFromUri(track.get("uri").asText()));
        item.setTitle(track.get("name").asText());
        item.setArtist(SpotifyUtils.extractArtists(track));
        item.setAlbum(track.get("album").get("name").asText());
        item.setImageUrl(SpotifyUtils.extractImageUrl(track.get("album")));
        item.setPlayedAt(playedAt.asText());

        return item;
    }
}
