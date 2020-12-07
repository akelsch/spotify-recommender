import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Navbar } from 'react-bulma-components';
import Avatar from 'react-avatar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';
import { selectUserImage, logout } from '../reducers/userReducer';

function UserAvatar() {
  const dispatch = useDispatch();
  const image = useSelector(selectUserImage);

  return (
    <Navbar.Item dropdown hoverable href="#">
      <Navbar.Link>
        {image ? (
          <Avatar src={image} size="28" round />
        ) : (
          <FontAwesomeIcon icon={faUser} />
        )}
      </Navbar.Link>
      <Navbar.Dropdown>
        <Navbar.Item
          onClick={async () =>
            (await SpotifyRecommenderApi.logout()) && dispatch(logout())
          }
        >
          Logout
        </Navbar.Item>
      </Navbar.Dropdown>
    </Navbar.Item>
  );
}

export default UserAvatar;
