import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle } from '@fortawesome/free-solid-svg-icons';
import ReactStars from 'react-rating-stars-component';

import styles from './SpotifyItem.module.css';

function SpotifyItem({ id, title, name, artist, imageUrl }) {
  const [isShown, setShown] = useState(false);

  let type;
  if (title && artist) {
    type = 'track';
  } else if (artist) {
    type = 'album';
  } else {
    type = 'artist';
  }

  const spotifyLink = `https://open.spotify.com/${type}/${id}`;

  let imageAlt;
  let textComponents;
  if (type === 'track') {
    imageAlt = `Cover of ${title} by ${artist}`;
    textComponents = (
      <>
        <p>{title}</p>
        <p>{artist}</p>
      </>
    );
  } else if (type === 'album') {
    imageAlt = `Cover of ${name} by ${artist}`;
    textComponents = (
      <>
        <p>{name}</p>
        <p>{artist}</p>
      </>
    );
  } else {
    imageAlt = `Cover of ${name}`;
    textComponents = (
      <>
        <p>{name}</p>
      </>
    );
  }

  return (
    <div className={styles.itemContainer}>
      <div
        className={styles.coverContainer}
        onMouseEnter={() => setShown(!isShown)}
        onMouseLeave={() => setShown(!isShown)}
      >
        {isShown && (
          <FontAwesomeIcon
            className={styles.coverPlayButton}
            icon={faPlayCircle}
            onClick={() => window.open(spotifyLink)}
          />
        )}
        <img src={imageUrl} alt={imageAlt} />
      </div>
      {textComponents}
      <ReactStars count={5} onChange={(e) => console.log(e)} size={24} isHalf />
    </div>
  );
}

SpotifyItem.propTypes = {
  id: PropTypes.string.isRequired,
  title: PropTypes.string,
  name: PropTypes.string,
  artist: PropTypes.string,
  imageUrl: PropTypes.string.isRequired,
};

SpotifyItem.defaultProps = {
  title: null,
  name: null,
  artist: null,
};

export default SpotifyItem;
