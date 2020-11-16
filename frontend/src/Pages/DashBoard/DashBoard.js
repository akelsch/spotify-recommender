import React from 'react';
import { useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';
import SongRowItemList from '../../Containers/SongRowItemList/SongRowItemList';
import Layout from '../../Containers/Layout/Layout';
import styles from './DashBoard.module.css';
import { selectRecentlyPlayedSongs } from '../../State/Slices/RecentlyPlayedSlice';

function DashBoard() {
  const recentlyPlayedSongs = useSelector(selectRecentlyPlayedSongs);
  return (
    <Layout>
      <Heading size={3} className={styles.headingRecentlyPlayed}>
        Recently Played
      </Heading>
      <SongRowItemList songItems={recentlyPlayedSongs} />
      <Heading size={3} className={styles.headingRecentlyPlayed}>
        Discover
      </Heading>
    </Layout>
  );
}

export default DashBoard;
