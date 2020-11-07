import React, { useState } from 'react';
import { Navbar, Heading } from 'react-bulma-components';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import 'react-bulma-components/dist/react-bulma-components.min.css';
import UserAvatar from '../../Components/UserAvatar/UserAvatar';

implfunction HeaderApp({ handleOnlineStatus, onlineStatus }) {
  const [active, setActive] = useState(false);

  return (
    <Navbar active={active}>
      <Navbar.Brand>
        <Navbar.Item>
          <Link to="/">
            <Heading>Spotify Recommender</Heading>
          </Link>
        </Navbar.Item>
        <Navbar.Burger onClick={() => setActive(!active)} />
      </Navbar.Brand>
      <Navbar.Menu>
        <Navbar.Container position="end">
          <Navbar.Item href="#">
            <Heading size={5}>Dashboard</Heading>
          </Navbar.Item>
          <Navbar.Item href="#">
            <Heading size={5}>Recently Played</Heading>
          </Navbar.Item>
          <Navbar.Item href="#">
            <Heading size={5}>Discover</Heading>
          </Navbar.Item>
          {onlineStatus ? (
            <Navbar.Item href="#">
              <UserAvatar handleOnlineStatus={handleOnlineStatus} />
            </Navbar.Item>
          ) : null}
        </Navbar.Container>
      </Navbar.Menu>
    </Navbar>
  );
}
HeaderApp.propTypes = {
  handleOnlineStatus: PropTypes.func.isRequired,
  onlineStatus: PropTypes.bool.isRequired,
};
export default HeaderApp;
