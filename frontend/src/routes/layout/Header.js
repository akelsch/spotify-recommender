import React, { useState, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Navbar, Container, Heading } from 'react-bulma-components';

import {
  selectUserOnline,
  selectUserImage,
  logout,
} from '../../reducers/userReducer';
import SpotifyRecommenderApi from '../../api/SpotifyRecommenderApi';
import Avatar from '../../components/common/Avatar';

function Header() {
  const dispatch = useDispatch();
  const isUserOnline = useSelector(selectUserOnline);
  const image = useSelector(selectUserImage);

  const [isActive, setActive] = useState(false);

  const logoutCallback = useCallback(async () => {
    try {
      await SpotifyRecommenderApi.logout();
    } catch (error) {
      // Session possibly not invalidated
    } finally {
      dispatch(logout());
    }
  }, [dispatch]);

  return (
    <header>
      <Navbar active={isActive} className="has-shadow">
        <Container>
          <Navbar.Brand>
            <Navbar.Item href="/">
              <Heading size={4} renderAs="span">
                Spotify Recommender
              </Heading>
            </Navbar.Item>
            <Navbar.Burger onClick={() => setActive(!isActive)} />
          </Navbar.Brand>
          <Navbar.Menu>
            <Navbar.Container position="end">
              <Navbar.Item href="/dashboard">Dashboard</Navbar.Item>
              <Navbar.Item href="/recently-played">Recently Played</Navbar.Item>
              <Navbar.Item href="/discover">Discover</Navbar.Item>
              {isUserOnline ? (
                <Avatar imageUrl={image} logoutCallback={logoutCallback} />
              ) : null}
            </Navbar.Container>
          </Navbar.Menu>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;
