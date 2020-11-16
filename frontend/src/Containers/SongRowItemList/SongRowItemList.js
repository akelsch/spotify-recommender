import React from 'react';
import PropTypes from 'prop-types';
import SongItem from '../../Components/SongItem/SongItem';
import styles from './SongRowItemList.module.css';

function SongRowItemList({ songItems }) {
  return (
    <div className={styles.songRowItemListContainer}>
      {[
        ...songItems.map(({ played_at, image_url, title, artist }) => (
          <SongItem
            key={played_at}
            songImgUrl={image_url}
            songTitle={title}
            songArtist={artist}
          />
        )),
      ]}
    </div>
  );
}

SongRowItemList.propTypes = {
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

export default SongRowItemList;
