import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Navbar, Container, Heading } from 'react-bulma-components';

import UserAvatar from '../../Components/UserAvatar/UserAvatar';
import { selectUserStatus } from '../../State/Slices/UserSlice';

function Header() {
  const [active, setActive] = useState(false);
  const onlineStatus = useSelector(selectUserStatus);

  return (
    <header>
      <Navbar active={active} className="has-shadow">
        <Container>
          <Navbar.Brand>
            <Navbar.Item href="/">
              <Heading size={4} renderAs="span">
                Spotify Recommender
              </Heading>
            </Navbar.Item>
            <Navbar.Burger onClick={() => setActive(!active)} />
          </Navbar.Brand>
          <Navbar.Menu>
            <Navbar.Container position="end">
              <Navbar.Item href="#">Dashboard</Navbar.Item>
              <Navbar.Item href="/recently-played">Recently Played</Navbar.Item>
              <Navbar.Item href="#">Discover</Navbar.Item>
              {onlineStatus ? <UserAvatar /> : null}
            </Navbar.Container>
          </Navbar.Menu>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;
