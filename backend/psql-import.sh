#!/bin/bash

if [[ $# -ne 1 ]]; then
  echo "Usage: psql-import.sh <password>"
  exit 2
fi

PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "DELETE FROM Tracks; DELETE FROM Playlists;"

for FILE in *.csv; do
  if [[ $FILE == playlists* ]]; then
    PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "COPY Playlists FROM '$PWD/$FILE' CSV HEADER;"
  elif [[ $FILE == tracks* ]]; then
    PGPASSWORD=$1 psql -U sptfyusr -d postgres -c "COPY Tracks(pid,track_uri,artist_uri,album_uri,pos) FROM '$PWD/$FILE' CSV HEADER;"
  fi
done
