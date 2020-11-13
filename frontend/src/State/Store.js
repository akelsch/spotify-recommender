import { configureStore } from '@reduxjs/toolkit';
import userReducer from './Slices/UserSlice';
import avatarReducer from './Slices/AvatarSlice';

export default configureStore({
  reducer: {
    userStatus: userReducer,
    avatarImage: avatarReducer,
  },
});
