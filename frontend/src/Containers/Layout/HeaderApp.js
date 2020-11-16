import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Navbar, Heading } from 'react-bulma-components';
import UserAvatar from '../../Components/UserAvatar/UserAvatar';
import { selectUserStatus } from '../../State/Slices/UserSlice';

function HeaderApp() {
  const [active, setActive] = useState(false);
  const onlineStatus = useSelector(selectUserStatus);

  return (
    <Navbar active={active} className="has-shadow">
      <Navbar.Brand>
        <Navbar.Item href="/">
          <Heading>Spotify Recommender</Heading>
        </Navbar.Item>
        <Navbar.Burger onClick={() => setActive(!active)} />
      </Navbar.Brand>
      <Navbar.Menu>
        <Navbar.Container position="end">
          <Navbar.Item href="#">
            <Heading size={5}>Dashboard</Heading>
          </Navbar.Item>
          <Navbar.Item href="/recently-played">
            <Heading size={5}>Recently Played</Heading>
          </Navbar.Item>
          <Navbar.Item href="#">
            <Heading size={5}>Discover</Heading>
          </Navbar.Item>
          {onlineStatus ? (
            <Navbar.Item href="#">
              <UserAvatar />
            </Navbar.Item>
          ) : null}
        </Navbar.Container>
      </Navbar.Menu>
    </Navbar>
  );
}

export default HeaderApp;
