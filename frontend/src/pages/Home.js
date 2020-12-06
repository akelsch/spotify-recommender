import React from 'react';
import { useSelector } from 'react-redux';
import { Hero, Heading, Button, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify } from '@fortawesome/free-brands-svg-icons';

import { selectUser } from '../state/slices/newUserSlice';
import Layout from './layout/Layout';

function Home() {
  const user = useSelector(selectUser);

  return (
    <Layout>
      <Hero>
        <Hero.Body>
          <Heading>Discover new music on Spotify easily</Heading>
          <Heading subtitle renderAs="h2">
            Login below to get started now!
          </Heading>
          {user ? null : (
            <Button
              size="medium"
              color="success"
              onClick={async () => {
                window.location.href =
                  'http://localhost:8080/oauth2/authorization/spotify';
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
