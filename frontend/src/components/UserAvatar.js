import React from 'react';
import { useSelector } from 'react-redux';
import { Navbar } from 'react-bulma-components';
import Avatar from 'react-avatar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

import { selectAvatarImage } from '../state/slices/AvatarSlice';

function UserAvatar() {
  const imageUrl = useSelector(selectAvatarImage);

  return (
    <Navbar.Item dropdown hoverable href="#">
      <Navbar.Link>
        {imageUrl === '' ? (
          <FontAwesomeIcon icon={faUser} />
        ) : (
          <Avatar src={imageUrl} size="28" round />
        )}
      </Navbar.Link>
      <Navbar.Dropdown>
        <Navbar.Item href="#">Logout</Navbar.Item>
      </Navbar.Dropdown>
    </Navbar.Item>
  );
}

export default UserAvatar;
