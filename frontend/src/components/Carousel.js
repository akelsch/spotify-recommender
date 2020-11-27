import React from 'react';
import PropTypes from 'prop-types';
import RCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import SongItem from './SongItem';

function Carousel({ songItems }) {
  const songItemsComponents = [
    ...songItems.map(({ played_at, image_url, title, artist }) => (
      <SongItem
        key={played_at}
        songImgUrl={image_url}
        songTitle={title}
        songArtist={artist}
      />
    )),
  ];
  return (
    <div className="is-flex is-flex-wrap-wrap">
      <RCarousel
        items={songItemsComponents}
        mouseTracking
        responsive={{
          0: { items: 1 },
          568: { items: 3 },
          1024: { items: 5 },
        }}
      />
    </div>
  );
}
Carousel.propTypes = {
  songItems: PropTypes.arrayOf(
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

export default Carousel;
