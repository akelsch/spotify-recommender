DROP FUNCTION IF EXISTS my_jaccard;
DROP FUNCTION IF EXISTS my_jaccard_tracks;
DROP FUNCTION IF EXISTS my_jaccard_albums;
DROP FUNCTION IF EXISTS my_jaccard_artists;

/* Base function */
CREATE FUNCTION my_jaccard(VARIADIC track_uris varchar[]) RETURNS TABLE(jaccard numeric, pid_fk integer) AS $$
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

/* Type-specific functions */
CREATE FUNCTION my_jaccard_tracks(VARIADIC track_uris varchar[]) RETURNS TABLE(track_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.track_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    GROUP BY tr.track_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION my_jaccard_albums(VARIADIC track_uris varchar[]) RETURNS TABLE(album_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.album_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    GROUP BY tr.album_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION my_jaccard_artists(VARIADIC track_uris varchar[]) RETURNS TABLE(artist_uri varchar, score numeric) AS $$
BEGIN
    RETURN QUERY
    SELECT tr.artist_uri, SUM(ja.jaccard) AS score
    FROM my_jaccard(VARIADIC track_uris) ja JOIN tracks tr ON ja.pid_fk = tr.pid_fk
    GROUP BY tr.artist_uri
    ORDER BY score DESC
    LIMIT 20;
END;
$$ LANGUAGE plpgsql;
