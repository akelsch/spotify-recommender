package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SpotifyItemDeserializer extends JsonDeserializer<RecentlyPlayedItem> {

    @Override
    public RecentlyPlayedItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // TODO implement conversion
//        Kein Context -> Song
//        Context "album" -> Album
//        Context "artist" -> Artist
//        Context "playlist" -> ???
        TreeNode treeNode = p.readValueAsTree();
        return null;
    }
}
