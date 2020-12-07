import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import {
  selectRecentlyPlayedTracks,
  setRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';
import Layout from './layout/Layout';
import SongItemList from '../components/SongItemList';

function RecentlyPlayed() {
  const dispatch = useDispatch();
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);

  useEffect(() => {
    const fetchData = async () => {
      const newTracks = await SpotifyRecommenderApi.fetchRecentlyPlayedTracks();
      dispatch(setRecentlyPlayedTracks(newTracks));
    };
    fetchData();
  }, [dispatch]);

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
