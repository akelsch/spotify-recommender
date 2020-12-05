import React from 'react';
import PropTypes from 'prop-types';
import SwiperCore, { Pagination } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';
import SongItem from './SongItem';

SwiperCore.use([Pagination]);

function Carousel({ songItems }) {
  const songItemsComponents = [
    ...songItems.map(({ played_at, id, image_url, title, artist }) => (
      <SwiperSlide key={played_at}>
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
      breakpoints={{
        640: {
          width: 320,
          slidesPerView: 1,
        },
        768: {
          width: 768,
          spaceBetween: 15,
          slidesPerView: 2,
        },
        961: {
          width: 961,
          spaceBetween: 20,
          slidesPerView: 3,
        },
        1025: {
          width: 1300,
          spaceBetween: 20,
          slidesPerView: 5,
        },
      }}
      pagination={{ clickable: true }}
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
