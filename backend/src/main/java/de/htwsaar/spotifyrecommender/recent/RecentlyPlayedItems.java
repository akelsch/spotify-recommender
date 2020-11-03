package de.htwsaar.spotifyrecommender.recent;

import lombok.Data;

import java.util.List;

@Data
public class RecentlyPlayedItems {

    private List<RecentlyPlayedItem> items;
}
