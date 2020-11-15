import { configureStore } from '@reduxjs/toolkit';
import userReducer from './Slices/UserSlice';
import avatarReducer from './Slices/AvatarSlice';
import recentlyPlayedReducer from './Slices/RecentlyPlayedSlice';

export default configureStore({
  reducer: {
    userStatus: userReducer,
    avatarImage: avatarReducer,
    recentlyPlayed: recentlyPlayedReducer,
  },
});
