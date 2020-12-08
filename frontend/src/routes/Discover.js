import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import {
  selectDiscoverTracks,
  setDiscoverTracks,
} from '../reducers/discoverReducer';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';
import Layout from './layout/Layout';
import Headline from '../components/Headline';
import SettingsForm from '../components/SettingsForm';
import Carousel from '../components/Carousel';

function Discover() {
  const dispatch = useDispatch();
  const discoverTracks = useSelector(selectDiscoverTracks);

  useEffect(() => {
    const fetchData = async () => {
      const tracks = await SpotifyRecommenderApi.fetchDiscoverTracks();
      dispatch(setDiscoverTracks(tracks));
    };
    fetchData();
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
        <Carousel heading="Tracks" tracks={discoverTracks} />
      ) : null}
    </Layout>
  );
}

export default Discover;
