import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Navbar, Container, Heading } from 'react-bulma-components';

import UserAvatar from '../../components/UserAvatar';
import { selectUser } from '../../reducers/userReducer';

function Header() {
  const [isActive, setActive] = useState(false);
  const user = useSelector(selectUser);

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
              {user ? <UserAvatar /> : null}
            </Navbar.Container>
          </Navbar.Menu>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;
