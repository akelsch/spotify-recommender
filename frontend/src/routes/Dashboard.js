import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { selectUserName } from '../reducers/userReducer';
import {
  selectRecentlyPlayedTracks,
  fetchRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import {
  selectDiscoverTracks,
  fetchDiscoverTracks,
} from '../reducers/discoverReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import CoverCarousel from '../components/CoverCarousel';

function Dashboard() {
  const dispatch = useDispatch();
  const userName = useSelector(selectUserName);
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);
  const discoverTracks = useSelector(selectDiscoverTracks);

  useEffect(() => {
    dispatch(fetchRecentlyPlayedTracks());
    dispatch(fetchDiscoverTracks());
  }, [dispatch]);

  return (
    <Layout>
      <Headline
        title="Dashboard"
        subtitle={`Welcome back, ${userName}! How is it going?`}
      />

      {recentlyPlayedTracks.length ? (
        <CoverCarousel heading="Recently Played" items={recentlyPlayedTracks} />
      ) : null}

      {discoverTracks.length ? (
        <CoverCarousel heading="Discover" items={discoverTracks} />
      ) : null}
    </Layout>
  );
}

export default Dashboard;
