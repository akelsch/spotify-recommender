package de.htwsaar.spotifyrecommender.discover;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends ReactiveCrudRepository<Track, Long> {
}
