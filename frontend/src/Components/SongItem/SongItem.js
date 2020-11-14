import React from 'react';
import PropTypes from 'prop-types';
import styles from './SongItem.module.css';

function SongItem({ songImgUrl, songTitle }) {
  return (
    <div className={styles.songWrapper}>
      <img src={songImgUrl} alt="songCover" className={styles.songImage} />
      <p>{songTitle}</p>
    </div>
  );
}

SongItem.propTypes = {
  songImgUrl: PropTypes.string.isRequired,
  songTitle: PropTypes.string.isRequired,
};
export default SongItem;
