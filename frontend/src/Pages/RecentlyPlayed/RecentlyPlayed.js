import React from 'react';
import { useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';
import SongItemList from '../../Containers/SongItemList/SongItemList';
import Layout from '../../Containers/Layout/Layout';
import styles from './RecentlyPlayed.module.css';
import { selectRecentlyPlayedSongs } from '../../State/Slices/RecentlyPlayedSlice';

function RecentlyPlayed() {
  const recentlyPlayedSongs = useSelector(selectRecentlyPlayedSongs);
  return (
    <Layout>
      <Heading size={3} className={styles.headingRecentlyPlayed}>
        RecentlyPlayed
      </Heading>
      <SongItemList songItems={recentlyPlayedSongs} />
    </Layout>
  );
}

export default RecentlyPlayed;
