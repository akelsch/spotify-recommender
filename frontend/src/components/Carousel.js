import React, { useState, useRef, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Heading } from 'react-bulma-components';
import SwiperCore, { Pagination, Virtual } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';

import TrackItem from './TrackItem';

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

function Carousel({ heading, tracks }) {
  const [swiper, setSwiper] = useState(null);
  const tracksRef = useRef(tracks);

  useEffect(() => {
    if (swiper && tracks !== tracksRef.current) {
      swiper.updateSlides();
    }
  }, [swiper, tracks]);

  return (
    <div>
      <Heading renderAs="h2" className="mb-0">
        {heading}
      </Heading>
      <Swiper onSwiper={setSwiper} {...params}>
        {tracks.map(({ id, title, artist, image_url, played_at }, index) => (
          <SwiperSlide key={played_at || id + index} virtualIndex={index}>
            <TrackItem
              id={id}
              title={title}
              artist={artist}
              imageUrl={image_url}
            />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}

Carousel.propTypes = {
  heading: PropTypes.string.isRequired,
  tracks: PropTypes.arrayOf(
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
