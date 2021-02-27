import React from 'react';
import { useSelector } from 'react-redux';
import {
  selectDiscoverTracks,
  selectDiscoverAlbums,
  selectDiscoverArtists,
} from '../reducers/discoverReducer';

import { selectRecentlyPlayedTracks } from '../reducers/recentlyPlayedReducer';
import { selectRatings } from '../reducers/ratingReducer';
import Layout from './layout/Layout';
import RatingListItem from '../components/RatingListItems';
import Headline from '../components/common/Headline';

function Ratings() {
  const tracks = useSelector(selectRecentlyPlayedTracks);
  const discoverTracks = useSelector(selectDiscoverTracks);
  const discoverAlbums = useSelector(selectDiscoverAlbums);
  const discoverArtists = useSelector(selectDiscoverArtists);
  const ratings = useSelector(selectRatings);

  const searchRatingForItem = (item) =>
    ratings.find(({ uri }) => uri === item.id);

  const ratedTracks = [...discoverTracks, ...tracks].filter(
    searchRatingForItem
  );

  const ratedAlbums = discoverAlbums.filter(searchRatingForItem);

  const ratedArtists = discoverArtists.filter(searchRatingForItem);

  return (
    <Layout>
      <Headline
        title="Ratings"
        subtitle="Review your rated tracks, albums and artists"
      />
      <RatingListItem
        items={[...discoverTracks, ...tracks]}
        ratings={ratings}
      />
    </Layout>
  );
}

export default Ratings;
