import React from 'react';
import PropTypes from 'prop-types';

import SpotifyItem from './SpotifyItem';

function CoverGrid({ tracks }) {
  return (
    <div className="is-flex is-flex-wrap-wrap">
      {tracks.map(({ id, title, artist, image_url, played_at }) => (
        <SpotifyItem
          key={played_at}
          id={id}
          title={title}
          artist={artist}
          imageUrl={image_url}
        />
      ))}
    </div>
  );
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
};

export default CoverGrid;
