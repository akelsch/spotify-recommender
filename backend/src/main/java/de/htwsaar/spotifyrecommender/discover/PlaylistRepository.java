package de.htwsaar.spotifyrecommender.discover;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends ReactiveCrudRepository<Playlist, Long> {
}
