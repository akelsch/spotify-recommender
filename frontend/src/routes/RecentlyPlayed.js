import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import {
  selectRecentlyPlayedTracks,
  setRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';
import Layout from './layout/Layout';
import Headline from '../components/Headline';
import TrackItemGrid from '../components/TrackItemGrid';

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
      <Headline
        title="Recently Played"
        subtitle="Here's what you have been up to recently"
      />
      <TrackItemGrid tracks={recentlyPlayedTracks} />
    </Layout>
  );
}

export default RecentlyPlayed;
