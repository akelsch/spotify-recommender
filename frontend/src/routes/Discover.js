import React, { useEffect, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import {
  selectDiscoverTracks,
  selectDiscoverAlbums,
  selectDiscoverArtists,
  fetchDiscoverTracks,
  fetchDiscoverAlbums,
  fetchDiscoverArtists,
} from '../reducers/discoverReducer';
import { selectRatings, fetchRatings } from '../reducers/ratingReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import SettingsForm from '../components/SettingsForm';
import CoverCarousel from '../components/CoverCarousel';

function Discover() {
  const dispatch = useDispatch();
  const discoverTracks = useSelector(selectDiscoverTracks);
  const discoverAlbums = useSelector(selectDiscoverAlbums);
  const discoverArtists = useSelector(selectDiscoverArtists);
  const ratings = useSelector(selectRatings);

  useEffect(() => {
    dispatch(fetchRatings());
  }, [dispatch]);

  const updateCallback = useCallback(
    (queryParams) => {
      dispatch(fetchDiscoverTracks(queryParams));
      dispatch(fetchDiscoverAlbums(queryParams));
      dispatch(fetchDiscoverArtists(queryParams));
    },
    [dispatch]
  );

  return (
    <Layout>
      <Headline
        title="Discover"
        subtitle="Here's what we recommend checking out"
      />

      <div className="mb-5">
        <Heading renderAs="h2">Settings</Heading>
        <SettingsForm updateCallback={updateCallback} />
      </div>

      {discoverTracks.length ? (
        <CoverCarousel
          heading="Tracks"
          items={discoverTracks}
          ratings={ratings}
        />
      ) : null}

      {discoverAlbums.length ? (
        <CoverCarousel
          heading="Albums"
          items={discoverAlbums}
          ratings={ratings}
        />
      ) : null}

      {discoverArtists.length ? (
        <CoverCarousel
          heading="Artists"
          items={discoverArtists}
          ratings={ratings}
        />
      ) : null}
    </Layout>
  );
}

export default Discover;
