import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Avater from 'react-avatar';
import { Dropdown } from 'react-bulma-components';
import styles from './UserAvatar.module.css';
import { logout } from '../../State/Slices/UserSlice';

function UserAvatar() {
  const [imgUrl, setImgUrl] = useState('');
  const [selectedItem, setSelectedItem] = useState('');
  const dispatch = useDispatch();
  useEffect(async () => {
    const response = await axios.get(
      'https://jsonplaceholder.typicode.com/albums/1/photos/?id=2'
    );
    const jsonData = response.data[0];
    setImgUrl(jsonData.url);
  });

  const handleDropDownMenu = (selected) => {
    if (selected === 'logout') {
      dispatch(logout(false));
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

export default UserAvatar;
