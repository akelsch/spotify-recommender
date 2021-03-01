CREATE TYPE spotify_type AS ENUM ('track', 'artist', 'album');

CREATE TABLE playlists
(
    pid           integer NOT NULL,
    modified_at   bigint  NOT NULL,
    num_tracks    integer NOT NULL,
    num_albums    integer NOT NULL,
    num_followers integer NOT NULL,
    PRIMARY KEY (pid)
);

CREATE TABLE tracks
(
    id         serial      NOT NULL,
    pid_fk     integer     NOT NULL,
    track_uri  varchar(50) NOT NULL,
    artist_uri varchar(50) NOT NULL,
    album_uri  varchar(50) NOT NULL,
    pos        integer     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pid_fk) REFERENCES playlists
);

CREATE TABLE ratings
(
    id      serial       NOT NULL,
    user_id varchar(50)  NOT NULL,
    uri     varchar(50)  NOT NULL,
    type    spotify_type NOT NULL,
    rating  real         NOT NULL,
    PRIMARY KEY (id)
);
