package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;

import java.io.IOException;

public class SpotifyItemDeserializer extends JsonDeserializer<RecentlyPlayedItem> {

    @Override
    public RecentlyPlayedItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.readValueAsTree();
        RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem();

        TreeNode track = treeNode.get("track");

        String id = track.get("uri").toString().replace("\"","");
        recentlyPlayedItem.setId(id.substring(14));

        String name = track.get("name").toString().replace("\"","");
        recentlyPlayedItem.setName(name);

        // TODO set ImageUrl
        recentlyPlayedItem.setImageUrl("TODO");

        TreeNode context = treeNode.get("context");
        if(context instanceof NullNode) {
            recentlyPlayedItem.setType("Song");
        } else {
            String type = context.get("type").toString().replace("\"","");
            switch (type) {
                case "album" -> recentlyPlayedItem.setType("Album");
                case "artist" -> recentlyPlayedItem.setType("Artist");
                case "playlist" -> recentlyPlayedItem.setType("Playlist");
            }
        }

        return recentlyPlayedItem;
    }
}
