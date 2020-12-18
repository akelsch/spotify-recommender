import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import InfiniteScroll from 'react-infinite-scroll-component';
import {
  selectRecentlyPlayedTracks,
  fetchRecentlyPlayedTracks,
} from '../reducers/recentlyPlayedReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import CoverGrid from '../components/CoverGrid';

function RecentlyPlayed() {
  const dispatch = useDispatch();
  const recentlyPlayedTracks = useSelector(selectRecentlyPlayedTracks);

  useEffect(() => {
    dispatch(fetchRecentlyPlayedTracks());
  }, [dispatch]);

  return (
    <Layout>
      <Headline
        title="Recently Played"
        subtitle="Here's what you have been up to recently"
      />

      <CoverGrid tracks={recentlyPlayedTracks} />
    </Layout>
  );
}

export default RecentlyPlayed;
