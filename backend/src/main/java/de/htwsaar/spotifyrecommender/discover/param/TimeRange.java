package de.htwsaar.spotifyrecommender.discover.param;

/**
 * Request parameter for the "Get a User's Top Artists and Tracks" Spotify endpoint:
 * Over what time frame the affinities are computed.
 *
 * @see Source#top
 */
public enum TimeRange {

    /**
     * Several years of data and including all new data as it becomes available.
     */
    long_term,

    /**
     * Approximately last 6 months (default).
     */
    medium_term,

    /**
     * Approximately last 4 weeks.
     */
    short_term
}
