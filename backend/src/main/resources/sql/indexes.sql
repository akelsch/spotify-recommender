CREATE TABLE playlist_lengths AS
SELECT COUNT(*) AS len, pid_fk
FROM tracks
GROUP BY pid_fk;

CREATE INDEX ON playlist_lengths (pid_fk) INCLUDE (len);
CREATE INDEX ON tracks (track_uri) INCLUDE (pid_fk);
CREATE INDEX ON tracks (pid_fk) INCLUDE (track_uri);
CREATE INDEX ON tracks (pid_fk) INCLUDE (artist_uri);
CREATE INDEX ON tracks (pid_fk) INCLUDE (album_uri);
