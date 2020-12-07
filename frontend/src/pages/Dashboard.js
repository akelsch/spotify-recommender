import React, { useEffect } from 'react';
import { Heading } from 'react-bulma-components';
import { useDispatch, useSelector } from 'react-redux';

import { selectUserName } from '../reducers/userReducer';
import {
  selectRecentlyPlayedTracks,
  setRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import Layout from './layout/Layout';
import Carousel from '../components/Carousel';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

function Dashboard() {
  const dispatch = useDispatch();
  const userName = useSelector(selectUserName);
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);
  const initialized = recentlyPlayedTracks.length;

  useEffect(() => {
    const fetchData = async () => {
      const tracks = await SpotifyRecommenderApi.fetchRecentlyPlayedTracks();
      dispatch(setRecentlyPlayedTracks(tracks));
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
        {initialized ? <Carousel songItems={recentlyPlayedTracks} /> : null}
      </div>

      <div>
        <Heading renderAs="h2" className="mb-0">
          Discover
        </Heading>
        {initialized ? (
          <Carousel songItems={recentlyPlayedTracks.slice().reverse()} />
        ) : null}
      </div>
    </Layout>
  );
}

export default Dashboard;
