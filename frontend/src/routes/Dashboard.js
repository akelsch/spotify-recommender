import React, { useEffect } from 'react';
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
import Headline from '../components/Headline';
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
      <Headline
        title="Dashboard"
        subtitle={`Welcome back, ${userName}! How is it going?`}
      />

      {recentlyPlayedTracks.length ? (
        <Carousel heading="Recently Played" tracks={recentlyPlayedTracks} />
      ) : null}

      {discoverTracks.length ? (
        <Carousel heading="Discover" tracks={discoverTracks} />
      ) : null}
    </Layout>
  );
}

export default Dashboard;
