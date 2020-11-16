import React from 'react';
import PropTypes from 'prop-types';
import SongItem from '../../Components/SongItem/SongItem';
import styles from './SongItemList.module.css';

function SongItemList({ songItems }) {
  return (
    <div className={styles.songItemListContainer}>
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

SongItemList.propTypes = {
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

export default SongItemList;
