import React, { useEffect } from 'react';
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
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';
import SettingsForm from '../components/SettingsForm';
import CoverCarousel from '../components/CoverCarousel';

function Discover() {
  const dispatch = useDispatch();
  const discoverTracks = useSelector(selectDiscoverTracks);
  const discoverAlbums = useSelector(selectDiscoverAlbums);
  const discoverArtists = useSelector(selectDiscoverArtists);

  useEffect(() => {
    dispatch(fetchDiscoverTracks());
    dispatch(fetchDiscoverAlbums());
    dispatch(fetchDiscoverArtists());
  }, [dispatch]);

  return (
    <Layout>
      <Headline
        title="Discover"
        subtitle="Here's what we recommend checking out"
      />

      <div className="mb-5">
        <Heading renderAs="h2">Settings</Heading>
        <SettingsForm />
      </div>

      {discoverTracks.length ? (
        <CoverCarousel heading="Tracks" items={discoverTracks} />
      ) : null}

      {discoverAlbums.length ? (
        <CoverCarousel heading="Albums" items={discoverAlbums} />
      ) : null}

      {discoverArtists.length ? (
        <CoverCarousel heading="Artists" items={discoverArtists} />
      ) : null}
    </Layout>
  );
}

export default Discover;
