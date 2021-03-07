import React from 'react';
import { useSelector } from 'react-redux';
import { Hero, Button, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify } from '@fortawesome/free-brands-svg-icons';

import { LOGIN_URL } from '../api/SpotifyRecommenderApi';
import { selectUserOnline } from '../reducers/userReducer';
import Layout from './layout/Layout';
import Headline from '../components/common/Headline';

function Home() {
  const isUserOnline = useSelector(selectUserOnline);

  return (
    <Layout>
      <Hero>
        <Hero.Body>
          <Headline
            title="Discover new music on Spotify easily"
            subtitle="Login below to get started now!"
          />
          {isUserOnline ? null : (
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
          )}
        </Hero.Body>
      </Hero>
    </Layout>
  );
}

export default Home;
