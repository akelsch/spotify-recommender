import React from 'react';
import PropTypes from 'prop-types';
import Rating from 'react-rating-stars-component';
import { Container } from 'react-bulma-components';
import styles from './SongItem.module.css';

function SongItem({ songImgUrl, songTitle }) {
  return (
    <Container>
      <div className={styles.songWrapper}>
        <img src={songImgUrl} alt="songCover" className={styles.songImage} />
        <p>{songTitle}</p>
        <Rating
          count={5}
          isHalf
          onChange={(e) => console.log(e)}
          size={24}
          activeColor="#ffd700"
        />
      </div>
    </Container>
  );
}

SongItem.propTypes = {
  songImgUrl: PropTypes.string.isRequired,
  songTitle: PropTypes.string.isRequired,
};
export default SongItem;
