import React, { useEffect, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import {
  selectRecentlyPlayedTracks,
  selectRecentlyPlayedBeforeCursor,
  fetchRecentlyPlayed,
  updateRecentlyPlayed,
} from '../reducers/recentlyPlayedReducer';
import { selectRatings, fetchRatings } from '../reducers/ratingReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import CoverGrid from '../components/CoverGrid';

function RecentlyPlayed() {
  const dispatch = useDispatch();
  const tracks = useSelector(selectRecentlyPlayedTracks);
  const before = useSelector(selectRecentlyPlayedBeforeCursor);
  const ratings = useSelector(selectRatings);

  useEffect(() => {
    dispatch(fetchRecentlyPlayed());
    dispatch(fetchRatings());
  }, [dispatch]);
  return (
    <Layout>
      <Headline
        title="Recently Played"
        subtitle="Here's what you have been up to recently"
      />
      <CoverGrid
        tracks={tracks}
        ratings={ratings || []}
        updateCallback={useCallback(
          () => dispatch(updateRecentlyPlayed(before)),
          [dispatch, before]
        )}
      />
    </Layout>
  );
}

export default RecentlyPlayed;
