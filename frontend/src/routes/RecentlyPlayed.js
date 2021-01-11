import React, { useEffect, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import {
  selectRecentlyPlayedTracks,
  selectRecentlyPlayedBeforeCursor,
  fetchRecentlyPlayed,
  updateRecentlyPlayed,
} from '../reducers/recentlyPlayedReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import CoverGrid from '../components/CoverGrid';

function RecentlyPlayed() {
  const dispatch = useDispatch();
  const tracks = useSelector(selectRecentlyPlayedTracks);
  const before = useSelector(selectRecentlyPlayedBeforeCursor);

  useEffect(() => {
    dispatch(fetchRecentlyPlayed());
  }, [dispatch]);

  return (
    <Layout>
      <Headline
        title="Recently Played"
        subtitle="Here's what you have been up to recently"
      />
      <CoverGrid
        tracks={tracks}
        updateCallback={useCallback(
          () => dispatch(updateRecentlyPlayed(before)),
          [dispatch, before]
        )}
      />
    </Layout>
  );
}

export default RecentlyPlayed;
