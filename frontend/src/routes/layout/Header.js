import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Navbar, Container, Heading } from 'react-bulma-components';

import {
  selectUserOnline,
  selectUserImage,
  logout,
} from '../../reducers/userReducer';
import SpotifyRecommenderApi from '../../api/SpotifyRecommenderApi';
import UserAvatar from '../../components/UserAvatar';

function Header() {
  const dispatch = useDispatch();
  const isUserOnline = useSelector(selectUserOnline);
  const image = useSelector(selectUserImage);
  const logoutCallback = async () =>
    (await SpotifyRecommenderApi.logout()) && dispatch(logout());

  const [isActive, setActive] = useState(false);

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
                <UserAvatar imageUrl={image} logoutCallback={logoutCallback} />
              ) : null}
            </Navbar.Container>
          </Navbar.Menu>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;