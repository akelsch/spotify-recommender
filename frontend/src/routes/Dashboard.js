import React, { useEffect } from 'react';
import { Heading } from 'react-bulma-components';
import { useDispatch, useSelector } from 'react-redux';

import { selectUserName } from '../reducers/userReducer';
import {
  selectRecentlyPlayedTracks,
  setRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import {
  selectDiscoverTracks,
  setDiscoverTracks,
} from '../reducers/discoverReducer';
import Layout from './layout/Layout';
import Carousel from '../components/Carousel';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

function Dashboard() {
  const dispatch = useDispatch();
  const userName = useSelector(selectUserName);
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);
  const discoverTracks = useSelector(selectDiscoverTracks);

  useEffect(() => {
    const fetchData = async () => {
      const newRecentlyPlayed = await SpotifyRecommenderApi.fetchRecentlyPlayedTracks();
      dispatch(setRecentlyPlayedTracks(newRecentlyPlayed));
      const newDiscover = await SpotifyRecommenderApi.fetchDiscoverTracks();
      dispatch(setDiscoverTracks(newDiscover));
    };
    fetchData();
  }, [dispatch]);

  return (
    <Layout>
      <Heading>Dashboard</Heading>
      <Heading subtitle renderAs="h2">
        {`Welcome back, ${userName}! How is it going?`}
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
        {discoverTracks.length ? <Carousel songItems={discoverTracks} /> : null}
      </div>
    </Layout>
  );
}

export default Dashboard;
