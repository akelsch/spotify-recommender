package de.htwsaar.spotifyrecommender.song;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends ReactiveCrudRepository<Song, String> {
}
