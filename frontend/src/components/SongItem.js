import React from 'react';
import PropTypes from 'prop-types';
import Rating from 'react-rating-stars-component';
import styles from './SongItem.module.css';

function SongItem({ songImgUrl, songTitle, songArtist }) {
  return (
    <div className={styles.songWrapper}>
      <img src={songImgUrl} alt="songCover" className={styles.songImage} />
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
  songImgUrl: PropTypes.string.isRequired,
  songTitle: PropTypes.string.isRequired,
  songArtist: PropTypes.string.isRequired,
};

export default SongItem;
