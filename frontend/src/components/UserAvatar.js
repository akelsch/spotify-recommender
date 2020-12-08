import React from 'react';
import PropTypes from 'prop-types';
import { Navbar } from 'react-bulma-components';
import Avatar from 'react-avatar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

function UserAvatar({ imageUrl, logoutCallback }) {
  return (
    <Navbar.Item dropdown hoverable href="#">
      <Navbar.Link>
        {imageUrl ? (
          <Avatar src={imageUrl} size="28" round />
        ) : (
          <FontAwesomeIcon icon={faUser} size="lg" />
        )}
      </Navbar.Link>
      <Navbar.Dropdown>
        <Navbar.Item onClick={logoutCallback}>Logout</Navbar.Item>
      </Navbar.Dropdown>
    </Navbar.Item>
  );
}

UserAvatar.propTypes = {
  imageUrl: PropTypes.string.isRequired,
  logoutCallback: PropTypes.func.isRequired,
};

export default UserAvatar;
