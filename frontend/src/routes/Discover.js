import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Heading } from 'react-bulma-components';

import Layout from './layout/Layout';
import SettingsForm from '../components/SettingsForm';
import Carousel from '../components/Carousel';

import {
  selectDiscoverTracks,
  setDiscoverTracks,
} from '../reducers/discoverReducer';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

function Discover() {
  const dispatch = useDispatch();
  const discoverTracks = useSelector(selectDiscoverTracks);
  const initialized = discoverTracks.length;

  useEffect(() => {
    const fetchData = async () => {
      const tracks = await SpotifyRecommenderApi.fetchDiscoverTracks();
      dispatch(setDiscoverTracks(tracks));
    };
    fetchData();
  }, [dispatch]);

  return (
    <Layout>
      <Heading>Discover</Heading>
      <Heading subtitle renderAs="h2">
        Here&apos;s what we recommend checking out
      </Heading>

      <div className="mb-5">
        <Heading renderAs="h2">Settings</Heading>
        <SettingsForm />
      </div>

      <div>
        <Heading renderAs="h2" className="mb-0">
          Tracks
        </Heading>
        {initialized ? <Carousel songItems={discoverTracks} /> : null}
      </div>
    </Layout>
  );
}

export default Discover;
