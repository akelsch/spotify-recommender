import React from 'react';
import { useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import { selectRecentlyPlayedTracks } from '../reducers/recentlyPlayedReducer';
import Layout from './layout/Layout';
import SongItemList from '../components/SongItemList';

function RecentlyPlayed() {
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);

  return (
    <Layout>
      <Heading>Recently Played</Heading>
      <Heading subtitle renderAs="h2">
        Here&apos;s what you have been up to recently
      </Heading>
      <SongItemList songItems={recentlyPlayedTracks} />
    </Layout>
  );
}

export default RecentlyPlayed;
