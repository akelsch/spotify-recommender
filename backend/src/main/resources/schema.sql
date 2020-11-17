CREATE TABLE Playlists
(
    pid           integer PRIMARY KEY,
    modified_at   bigint  NOT NULL,
    num_tracks    integer NOT NULL,
    num_albums    integer NOT NULL,
    num_followers integer NOT NULL
);

CREATE TABLE Tracks
(
    id         serial PRIMARY KEY,
    pid_fk     integer     NOT NULL,
    track_uri  varchar(50) NOT NULL,
    artist_uri varchar(50) NOT NULL,
    album_uri  varchar(50) NOT NULL,
    pos        integer     NOT NULL,
    CONSTRAINT FK_57 FOREIGN KEY (pid_fk) REFERENCES Playlists (pid)
);

CREATE INDEX fkIdx_57 ON Tracks (pid_fk);

CREATE TYPE spotify_type AS ENUM ('track', 'artist', 'album');

CREATE TABLE Ratings
(
    id      serial PRIMARY KEY,
    user_id varchar(50)  NOT NULL,
    uri     varchar(50)  NOT NULL,
    type    spotify_type NOT NULL,
    rating  real         NOT NULL
);
