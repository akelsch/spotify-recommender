import React from 'react';
import { useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import Layout from './layout/Layout';
import SongItemList from '../components/SongItemList';
import { selectRecentlyPlayedSongs } from '../state/slices/RecentlyPlayedSlice';

function RecentlyPlayed() {
  const recentlyPlayedSongs = useSelector(selectRecentlyPlayedSongs);
  return (
    <Layout>
      <Heading>Recently Played</Heading>
      <Heading subtitle renderAs="h2">
        Here&apos;s what you have been up to recently
      </Heading>
      <SongItemList songItems={recentlyPlayedSongs} />
    </Layout>
  );
}

export default RecentlyPlayed;
