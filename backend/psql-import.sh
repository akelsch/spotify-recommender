#!/bin/bash

if [[ $# -ne 1 ]]; then
  echo "Usage: psql-import.sh <password>"
  exit 2
fi

PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "DELETE FROM Tracks;"
PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "DELETE FROM Playlists;"
PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "ALTER SEQUENCE tracks_id_seq RESTART;"

for FILE in *.csv; do
  if [[ $FILE == playlists* ]]; then
    PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "COPY Playlists FROM '$PWD/$FILE' CSV HEADER;"
  elif [[ $FILE == tracks* ]]; then
    PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "COPY Tracks(pid_fk,track_uri,artist_uri,album_uri,pos) FROM '$PWD/$FILE' CSV HEADER;"
  fi
done
