import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Hero, Heading, Button, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify } from '@fortawesome/free-brands-svg-icons';

import Layout from '../../Containers/Layout/Layout';
import { login, selectUserStatus } from '../../State/Slices/UserSlice';

function Home() {
  const onlineStatus = useSelector(selectUserStatus);
  const dispatch = useDispatch();
  return (
    <Layout>
      <Hero>
        <Hero.Body>
          <Heading>Discover new music on Spotify easily</Heading>
          <Heading subtitle renderAs="h2">
            Login below to get started now!
          </Heading>
          {onlineStatus ? null : (
            <Button
              renderAs="a"
              href="http://localhost:8080/oauth2/authorization/spotify"
              size="medium"
              color="success"
              onClick={async () => dispatch(login(true))}
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
