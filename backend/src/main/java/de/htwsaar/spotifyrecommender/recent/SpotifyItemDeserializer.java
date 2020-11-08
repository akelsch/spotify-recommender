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

        // TODO set ImageUrl
        recentlyPlayedItem.setImageUrl("TODO");

        TreeNode context = treeNode.get("context");
        if(context instanceof NullNode) {
            recentlyPlayedItem.setType("Song");

            String id = track.get("uri").toString().replace("\"","");
            recentlyPlayedItem.setId(id.substring(14));

            String songName = track.get("name").toString().replace("\"","");
            recentlyPlayedItem.setName(songName);
        } else {
            String type = context.get("type").toString().replace("\"","");
            switch (type) {
                case "album" -> {
                    recentlyPlayedItem.setType("Album");

                    String albumId = track.get("album").get("uri").toString().replace("\"", "");
                    recentlyPlayedItem.setId(albumId.substring(14));

                    String albumName = track.get("album").get("name").toString().replace("\"", "");
                    recentlyPlayedItem.setName(albumName);
                }
                case "artist" -> {
                    recentlyPlayedItem.setType("Artist");
                    TreeNode artists = track.get("artists");
                    if(artists.size() > 1) {
                        String id = artists.get(0).get("uri").toString().replace("\"", "");
                        recentlyPlayedItem.setId(id.substring(14));

                        String name =artists.get(0).get("name").toString().replace("\"", "");
                        recentlyPlayedItem.setName(name);
                    } else {
                        String id = artists.get("uri").toString().replace("\"", "");
                        recentlyPlayedItem.setId(id.substring(14));

                        String name = artists.get("name").toString().replace("\"", "");
                        recentlyPlayedItem.setName(name);
                    }
                }
                case "playlist" -> {
                    recentlyPlayedItem.setType("Playlist");

                    String playlistId = track.get("uri").toString().replace("\"", "");
                    recentlyPlayedItem.setId(playlistId.substring(14));
                    
                    String playlistName = track.get("name").toString().replace("\"", "");
                    recentlyPlayedItem.setName(playlistName);
                }
            }
        }

        return recentlyPlayedItem;
    }
}
