import React from 'react';
import { Heading } from 'react-bulma-components';
import { useSelector } from 'react-redux';

import { selectUserName } from '../reducers/userReducer';
import { selectRecentlyPlayedTracks } from '../reducers/recentlyPlayedReducer';
import Layout from './layout/Layout';
import Carousel from '../components/Carousel';

function Dashboard() {
  const userName = useSelector(selectUserName);
  const greeting = `Welcome back, ${userName}! How is it going?`;
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);

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
        {recentlyPlayedTracks.length ? (
          <Carousel songItems={recentlyPlayedTracks} />
        ) : null}
      </div>

      <div>
        <Heading renderAs="h2" className="mb-0">
          Discover
        </Heading>
        {recentlyPlayedTracks.length ? (
          <Carousel songItems={recentlyPlayedTracks.slice().reverse()} />
        ) : null}
      </div>
    </Layout>
  );
}

export default Dashboard;
