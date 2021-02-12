import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { selectUserName } from '../reducers/userReducer';
import {
  selectRecentlyPlayedTracks,
  fetchRecentlyPlayed,
} from '../reducers/recentlyPlayedReducer';
import {
  selectDiscoverTracks,
  fetchDiscoverTracks,
} from '../reducers/discoverReducer';
import { selectRatings, fetchRatings } from '../reducers/ratingReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import CoverCarousel from '../components/CoverCarousel';

function Dashboard() {
  const dispatch = useDispatch();
  const userName = useSelector(selectUserName);
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);
  const discoverTracks = useSelector(selectDiscoverTracks);
  const ratings = useSelector(selectRatings);

  useEffect(() => {
    dispatch(fetchRecentlyPlayed());
    dispatch(fetchDiscoverTracks());
    dispatch(fetchRatings());
  }, [dispatch]);

  return (
    <Layout>
      <Headline
        title="Your ratings"
        subtitle={`Welcome back, ${userName}! How is it going?`}
      />

      {recentlyPlayedTracks.length ? (
        <CoverCarousel
          heading="Recently Played"
          items={recentlyPlayedTracks.slice(0, 20)}
          ratings={ratings}
        />
      ) : null}

      {discoverTracks.length ? (
        <CoverCarousel
          heading="Discover"
          items={discoverTracks}
          ratings={ratings}
        />
      ) : null}
    </Layout>
  );
}

export default Dashboard;
