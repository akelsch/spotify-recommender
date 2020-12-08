import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle } from '@fortawesome/free-solid-svg-icons';
import ReactStars from 'react-rating-stars-component';

import styles from './TrackItem.module.css';

function TrackItem({ id, title, artist, imageUrl }) {
  const [isShown, setShown] = useState(false);
  const spotifyLink = `https://open.spotify.com/track/${id}`;
  const altText = `Cover of ${title} by ${artist}`;

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
        <img src={imageUrl} alt={altText} />
      </div>
      <p>{title}</p>
      <p>{artist}</p>
      <ReactStars count={5} onChange={(e) => console.log(e)} size={24} isHalf />
    </div>
  );
}

TrackItem.propTypes = {
  id: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  artist: PropTypes.string.isRequired,
  imageUrl: PropTypes.string.isRequired,
};

export default TrackItem;
