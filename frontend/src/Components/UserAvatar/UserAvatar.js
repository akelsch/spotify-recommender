import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Avatar from 'react-avatar';
import { Dropdown } from 'react-bulma-components';
import styles from './UserAvatar.module.css';
import { logout } from '../../State/Slices/UserSlice';
import { selectAvatarImage } from '../../State/Slices/AvatarSlice';

function UserAvatar() {
  const [selectedItem, setSelectedItem] = useState('');
  const dispatch = useDispatch();
  const imageUrl = useSelector(selectAvatarImage);

  const handleDropDownMenu = (selected) => {
    if (selected === 'logout') {
      dispatch(logout(false));
    }
    setSelectedItem(selected);
  };

  return (
    <div>
      {imageUrl === '' ? null : (
        <Dropdown onChange={handleDropDownMenu} value={selectedItem}>
          <Dropdown.Item value="home" className={styles.dropDownMenuItems}>
            <Avatar rounded unstyled src={imageUrl} />
          </Dropdown.Item>
          <Dropdown.Item value="logout" className={styles.dropDownMenuItems} />
        </Dropdown>
      )}
    </div>
  );
}

export default UserAvatar;
