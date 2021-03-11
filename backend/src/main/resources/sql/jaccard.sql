DROP FUNCTION IF EXISTS my_jaccard;
DROP FUNCTION IF EXISTS my_jaccard_tracks;
DROP FUNCTION IF EXISTS my_jaccard_tracks_weights;
DROP FUNCTION IF EXISTS my_jaccard_albums;
DROP FUNCTION IF EXISTS my_jaccard_albums_weights;
DROP FUNCTION IF EXISTS my_jaccard_artists;
DROP FUNCTION IF EXISTS my_jaccard_artists_weights;

/* Base function */
CREATE FUNCTION my_jaccard(VARIADIC track_uris varchar[])
RETURNS TABLE(jaccard numeric, pid_fk integer) AS $$
BEGIN
    RETURN QUERY
    SELECT (COUNT(*)::numeric / (MAX(pl.len) + array_length(track_uris, 1) - COUNT(*))) AS jaccard, tr.pid_fk
    FROM tracks tr JOIN playlist_lengths pl ON tr.pid_fk = pl.pid_fk
    WHERE tr.track_uri = ANY(track_uris)
    GROUP BY tr.pid_fk
    ORDER BY jaccard DESC
    LIMIT 10;
END;
$$ LANGUAGE plpgsql;

/* Tracks */
CREATE FUNCTION my_jaccard_tracks(track_uris varchar[], filter_tracks boolean)
RETURNS TABLE(track_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.track_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    WHERE NOT (filter_tracks AND tr.track_uri = ANY(track_uris))
    GROUP BY tr.track_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION my_jaccard_tracks_weights(track_uris varchar[], filter_tracks boolean, rating_user varchar)
RETURNS TABLE(track_uri varchar, score double precision) AS $$
BEGIN
    RETURN QUERY
    SELECT ja.track_uri, ja.score * COALESCE(r.rating, 2.5) AS score
    FROM my_jaccard_tracks(track_uris, filter_tracks) ja
        LEFT OUTER JOIN ratings r ON (r.user_id = rating_user AND r.type = 'track' AND 'spotify:track:' || r.uri = ja.track_uri)
    ORDER BY score DESC;
END;
$$ LANGUAGE plpgsql;

/* Albums */
CREATE FUNCTION my_jaccard_albums(track_uris varchar[])
RETURNS TABLE(album_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.album_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    GROUP BY tr.album_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION my_jaccard_albums_weights(track_uris varchar[], rating_user varchar)
RETURNS TABLE(track_uri varchar, score double precision) AS $$
BEGIN
    RETURN QUERY
    SELECT ja.album_uri, ja.score * COALESCE(r.rating, 2.5) AS score
    FROM my_jaccard_albums(track_uris) ja
        LEFT OUTER JOIN ratings r ON (r.user_id = rating_user AND r.type = 'album' AND 'spotify:album:' || r.uri = ja.album_uri)
    ORDER BY score DESC;
END;
$$ LANGUAGE plpgsql;

/* Artists */
CREATE FUNCTION my_jaccard_artists(track_uris varchar[])
RETURNS TABLE(artist_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.artist_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    GROUP BY tr.artist_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION my_jaccard_artists_weights(track_uris varchar[], rating_user varchar)
RETURNS TABLE(track_uri varchar, score double precision) AS $$
BEGIN
    RETURN QUERY
    SELECT ja.artist_uri, ja.score * COALESCE(r.rating, 2.5) AS score
    FROM my_jaccard_artists(track_uris) ja
        LEFT OUTER JOIN ratings r ON (r.user_id = rating_user AND r.type = 'artist' AND 'spotify:artist:' || r.uri = ja.artist_uri)
    ORDER BY score DESC;
END;
$$ LANGUAGE plpgsql;
