CREATE TABLE Songs
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    image_uri VARCHAR(255) NOT NULL
);

CREATE TYPE spotify_type AS ENUM ('track', 'artist', 'album');

CREATE TABLE Track
(
    id         serial PRIMARY KEY,
    pid        integer     NOT NULL,
    track_uri  varchar(50) NOT NULL,
    artist_uri varchar(50) NOT NULL,
    album_uri  varchar(50) NOT NULL,
    pos        integer     NOT NULL,
    CONSTRAINT FK_57 FOREIGN KEY (pid) REFERENCES Playlist (pid)
);

CREATE INDEX fkIdx_57 ON Track (pid);

CREATE TABLE Playlist
(
    pid           integer PRIMARY KEY,
    modified_at   bigint  NOT NULL,
    num_tracks    integer NOT NULL,
    num_albums    integer NOT NULL,
    num_followers integer NOT NULL
);

CREATE TABLE Ratings
(
    id      serial PRIMARY KEY,
    user_id varchar(50)  NOT NULL,
    uri     varchar(50)  NOT NULL,
    type    spotify_type NOT NULL,
    rating  real         NOT NULL
);
