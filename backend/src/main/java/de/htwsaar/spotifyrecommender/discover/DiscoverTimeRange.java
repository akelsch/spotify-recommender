package de.htwsaar.spotifyrecommender.discover;

enum DiscoverTimeRange {
    /*
    Reference: https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-top-artists-and-tracks

    Over what time frame the affinities are computed. Valid values:
    - long_term (calculated from several years of data and including all new data as it becomes available),
    - medium_term (approximately last 6 months),
    - short_term (approximately last 4 weeks).
    Default: medium_term
     */

    long_term, medium_term, short_term
}
