import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Avater from 'react-avatar';
import { Dropdown } from 'react-bulma-components';
import PropTypes from 'prop-types';
import styles from './UserAvatar.module.css';

function UserAvatar({ handleOnlineStatus }) {
  const [imgUrl, setImgUrl] = useState('');
  const [selectedItem, setSelectedItem] = useState('');

  useEffect(async () => {
    const response = await axios.get(
      'https://jsonplaceholder.typicode.com/albums/1/photos/?id=2'
    );
    const jsonData = response.data[0];
    setImgUrl(jsonData.url);
  });

  const handleDropDownMenu = (selected) => {
    if (selected === 'logout') {
      handleOnlineStatus(false);
    }
    setSelectedItem(selected);
  };

  return (
    <div>
      {imgUrl === '' ? null : (
        <Dropdown onChange={handleDropDownMenu} value={selectedItem}>
          <Dropdown.Item value="home" className={styles.dropDownMenuItems}>
            <Avater rounded unstyled src={imgUrl} />
          </Dropdown.Item>

          <Dropdown.Item value="logout" className={styles.dropDownMenuItems}>
            <Link to="/">Logout</Link>
          </Dropdown.Item>
        </Dropdown>
      )}
    </div>
  );
}
UserAvatar.propTypes = {
  handleOnlineStatus: PropTypes.func.isRequired,
};

export default UserAvatar;
