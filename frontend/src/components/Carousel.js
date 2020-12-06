import React from 'react';
import PropTypes from 'prop-types';
import SwiperCore, { Pagination, Virtual } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';

import SongItem from './SongItem';

SwiperCore.use([Pagination, Virtual]);

function Carousel({ songItems }) {
  const songItemsComponents = [
    ...songItems.map(({ played_at, id, image_url, title, artist }, index) => (
      <SwiperSlide key={played_at} virtualIndex={index}>
        <SongItem
          songId={id}
          songImgUrl={image_url}
          songTitle={title}
          songArtist={artist}
        />
      </SwiperSlide>
    )),
  ];

  return (
    <Swiper
      slidesPerView={5}
      breakpoints={{
        0: {
          slidesPerView: 1,
        },
        768: {
          slidesPerView: 2,
        },
        1024: {
          slidesPerView: 3,
        },
        1216: {
          slidesPerView: 4,
        },
        1408: {
          slidesPerView: 5,
        },
      }}
      pagination={{ clickable: true }}
      virtual
    >
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
