import React from 'react';
import { useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import Layout from '../../Containers/Layout/Layout';
import SongItemList from '../../Containers/SongItemList/SongItemList';
import { selectRecentlyPlayedSongs } from '../../State/Slices/RecentlyPlayedSlice';

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
