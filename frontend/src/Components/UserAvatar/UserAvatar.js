import React from 'react';
import { useSelector } from 'react-redux';
import { Navbar } from 'react-bulma-components';
import Avatar from 'react-avatar';

import { selectAvatarImage } from '../../State/Slices/AvatarSlice';

function UserAvatar() {
  const imageUrl = useSelector(selectAvatarImage);

  return (
    <Navbar.Item dropdown hoverable href="#">
      <Navbar.Link>
        {imageUrl === '' ? null : <Avatar src={imageUrl} size="28" round />}
      </Navbar.Link>
      <Navbar.Dropdown>
        <Navbar.Item href="#">Logout</Navbar.Item>
      </Navbar.Dropdown>
    </Navbar.Item>
  );
}

export default UserAvatar;
