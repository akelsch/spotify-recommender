import React from 'react';
import { Heading } from 'react-bulma-components';
import { useSelector } from 'react-redux';
import { selectUserName } from '../state/slices/UserNameSlice';
import { selectRecentlyPlayedSongs } from '../state/slices/RecentlyPlayedSlice';
import Carousel from '../components/Carousel';

import Layout from './layout/Layout';

function Dashboard() {
  const userName = useSelector(selectUserName);
  const greeting = `Welcome back, ${userName}! How is it going?`;
  const recentlyPlayedSongs = useSelector(selectRecentlyPlayedSongs);

  return (
    <Layout>
      <Heading>Dashboard</Heading>
      <Heading subtitle renderAs="h2">
        {greeting}
      </Heading>

      <div>
        <Heading renderAs="h2" className="mb-0">
          Recently Played
        </Heading>
        {recentlyPlayedSongs.length ? (
          <Carousel songItems={recentlyPlayedSongs} />
        ) : null}
      </div>

      <div>
        <Heading renderAs="h2" className="mb-0">
          Discover
        </Heading>
        {recentlyPlayedSongs.length ? (
          <Carousel songItems={recentlyPlayedSongs.slice().reverse()} />
        ) : null}
      </div>
    </Layout>
  );
}

export default Dashboard;
