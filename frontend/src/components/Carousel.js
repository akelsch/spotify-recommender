import React, { useState, useRef, useEffect } from 'react';
import PropTypes from 'prop-types';
import SwiperCore, { Pagination, Virtual } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';

import SongItem from './SongItem';

SwiperCore.use([Pagination, Virtual]);

const params = {
  slidesPerView: 5,
  breakpoints: {
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
  },
  pagination: { clickable: true },
  virtual: true,
};

function Carousel({ songItems }) {
  const [swiper, setSwiper] = useState(null);
  const itemsRef = useRef(songItems);

  useEffect(() => {
    if (swiper && songItems !== itemsRef.current) {
      swiper.updateSlides();
    }
  }, [swiper, songItems]);

  return (
    <Swiper onSwiper={(s) => setSwiper(s)} {...params}>
      {songItems.map(({ id, title, artist, image_url, played_at }, index) => (
        <SwiperSlide key={played_at || id + index} virtualIndex={index}>
          <SongItem
            songId={id}
            songImgUrl={image_url}
            songTitle={title}
            songArtist={artist}
          />
        </SwiperSlide>
      ))}
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
      played_at: PropTypes.string,
    })
  ).isRequired,
};

export default Carousel;
