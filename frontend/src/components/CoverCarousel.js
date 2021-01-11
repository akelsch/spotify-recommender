import React, { useState, useRef, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Heading } from 'react-bulma-components';
import SwiperCore, { Pagination, Virtual } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';

import SpotifyItem from './SpotifyItem';

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

function CoverCarousel({ heading, items }) {
  const [swiper, setSwiper] = useState(null);
  const itemsRef = useRef(items);

  useEffect(() => {
    if (swiper && items !== itemsRef.current) {
      swiper.updateSlides();
    }
  }, [swiper, items]);

  return (
    <div>
      <Heading renderAs="h2" className="mb-0">
        {heading}
      </Heading>
      <Swiper onSwiper={setSwiper} {...params}>
        {items.map(
          ({ id, title, name, artist, image_url, played_at }, index) => (
            <SwiperSlide key={played_at || id + index} virtualIndex={index}>
              <SpotifyItem
                id={id}
                title={title}
                name={name}
                artist={artist}
                imageUrl={image_url}
              />
            </SwiperSlide>
          )
        )}
      </Swiper>
    </div>
  );
}

CoverCarousel.propTypes = {
  heading: PropTypes.string.isRequired,
  items: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      title: PropTypes.string,
      name: PropTypes.string,
      artist: PropTypes.string,
      album: PropTypes.string,
      image_url: PropTypes.string.isRequired,
      played_at: PropTypes.string,
    })
  ).isRequired,
};

export default CoverCarousel;
