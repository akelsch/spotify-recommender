import React from 'react';
import PropTypes from 'prop-types';
import { Navbar } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

import styles from './Avatar.module.css';

function Avatar({ imageUrl, logoutCallback }) {
  return (
    <Navbar.Item dropdown hoverable>
      <Navbar.Link>
        {imageUrl ? (
          <img src={imageUrl} alt="Avatar" className={styles.avatar} />
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

Avatar.propTypes = {
  imageUrl: PropTypes.string.isRequired,
  logoutCallback: PropTypes.func.isRequired,
};

export default Avatar;
