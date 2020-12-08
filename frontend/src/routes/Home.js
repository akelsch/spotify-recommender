import React from 'react';
import { useSelector } from 'react-redux';
import { Hero, Heading, Button, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify } from '@fortawesome/free-brands-svg-icons';

import { BACKEND_URL } from '../api/SpotifyRecommenderApi';
import { selectUserOnline } from '../reducers/userReducer';
import Layout from './layout/Layout';

function Home() {
  const isUserOnline = useSelector(selectUserOnline);

  return (
    <Layout>
      <Hero>
        <Hero.Body>
          <Heading>Discover new music on Spotify easily</Heading>
          <Heading subtitle renderAs="h2">
            Login below to get started now!
          </Heading>
          {isUserOnline ? null : (
            <Button
              size="medium"
              color="success"
              onClick={() => {
                window.location.href = `${BACKEND_URL}/oauth2/authorization/spotify`;
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
