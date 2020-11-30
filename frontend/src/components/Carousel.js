import React from 'react';
import PropTypes from 'prop-types';
import SwiperCore, { Pagination } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';
import SongItem from './SongItem';

SwiperCore.use([Pagination]);

function Carousel({ songItems }) {
  const songItemsComponents = [
    ...songItems.map(({ played_at, image_url, title, artist }) => (
      <SwiperSlide key={played_at}>
        <SongItem
          songImgUrl={image_url}
          songTitle={title}
          songArtist={artist}
        />
      </SwiperSlide>
    )),
  ];

  return (
    <Swiper slidesPerView={5} pagination={{ clickable: true }}>
      {songItemsComponents}
    </Swiper>
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
