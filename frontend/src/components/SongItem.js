import React, { useState } from 'react';
import PropTypes from 'prop-types';
import Rating from 'react-rating-stars-component';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle } from '@fortawesome/free-solid-svg-icons';
import styles from './SongItem.module.css';

function SongItem({ songId, songImgUrl, songTitle, songArtist }) {
  const [isShown, setIsShown] = useState(false);
  const playLintToSpotify = `https://open.spotify.com/track/${songId}`;
  return (
    <div className={styles.songWrapper}>
      <div
        className={styles.songImageAndIconWrapper}
        onMouseEnter={() => setIsShown(!isShown)}
        onMouseLeave={() => setIsShown(!isShown)}
      >
        {isShown && (
          <FontAwesomeIcon
            className={styles.songPlayButtonIcon}
            icon={faPlayCircle}
            onClick={() => window.open(playLintToSpotify)}
          />
        )}
        <img src={songImgUrl} alt="songCover" className={styles.songImage} />
      </div>
      <p>{songTitle}</p>
      <p>{songArtist}</p>
      <Rating
        count={5}
        isHalf
        onChange={(e) => console.log(e)}
        size={24}
        activeColor="#ffd700"
      />
    </div>
  );
}

SongItem.propTypes = {
  songId: PropTypes.string.isRequired,
  songImgUrl: PropTypes.string.isRequired,
  songTitle: PropTypes.string.isRequired,
  songArtist: PropTypes.string.isRequired,
};

export default SongItem;
