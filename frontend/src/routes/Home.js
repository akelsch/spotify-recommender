import React from 'react';
import { Hero, Button, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify } from '@fortawesome/free-brands-svg-icons';

import { LOGIN_URL } from '../api/SpotifyRecommenderApi';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';

function Home() {
  return (
    <Layout>
      <Hero>
        <Hero.Body>
          <Headline
            title="Discover new music on Spotify easily"
            subtitle="Login below to get started now!"
          />
          <Button
            size="medium"
            color="success"
            onClick={() => {
              window.location.href = LOGIN_URL;
            }}
          >
            <Icon>
              <FontAwesomeIcon icon={faSpotify} />
            </Icon>
            <span>Login with Spotify</span>
          </Button>
        </Hero.Body>
      </Hero>
    </Layout>
  );
}

export default Home;
