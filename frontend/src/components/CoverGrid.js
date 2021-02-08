import React, { useRef, useEffect } from 'react';
import PropTypes from 'prop-types';
import { useInView } from 'react-intersection-observer';

import SpotifyItem from './SpotifyItem';

function CoverGrid({ tracks, updateCallback, ratings }) {
  const [sentinel, inView, entry] = useInView();
  const entryRef = useRef(entry);

  useEffect(() => {
    if (inView) {
      if (entryRef.current !== entry) {
        entryRef.current = entry;
        if (tracks.length < 50) {
          updateCallback();
        }
      }
    }
  }, [inView, entry, tracks, updateCallback]);

  const components = tracks.map(
    ({ id, title, artist, image_url, played_at }) => {
      const rating = ratings.find(({ uri }) => uri === id);
      return (
        <SpotifyItem
          key={played_at}
          id={id}
          title={title}
          artist={artist}
          imageUrl={image_url}
          ratingObject={rating}
        />
      );
    }
  );

  components.push(
    <div key="sentinel" ref={sentinel} className="is-align-self-flex-end" />
  );

  return <div className="is-flex is-flex-wrap-wrap">{components}</div>;
}

CoverGrid.propTypes = {
  tracks: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      title: PropTypes.string.isRequired,
      artist: PropTypes.string.isRequired,
      album: PropTypes.string.isRequired,
      image_url: PropTypes.string.isRequired,
      played_at: PropTypes.string.isRequired,
    })
  ).isRequired,
  updateCallback: PropTypes.func.isRequired,
  ratings: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      user_id: PropTypes.string,
      uri: PropTypes.string,
      type: PropTypes.string,
      rating: PropTypes.number,
    })
  ).isRequired,
};

export default CoverGrid;
