import React from 'react';
import PropTypes from 'prop-types';

import TrackItem from './TrackItem';

function TrackItemGrid({ tracks }) {
  return (
    <div className="is-flex is-flex-wrap-wrap">
      {tracks.map(({ id, title, artist, image_url, played_at }) => (
        <TrackItem
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

TrackItemGrid.propTypes = {
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

export default TrackItemGrid;
