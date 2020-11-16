import { configureStore } from '@reduxjs/toolkit';
import userReducer from './slices/UserSlice';
import avatarReducer from './slices/AvatarSlice';
import recentlyPlayedReducer from './slices/RecentlyPlayedSlice';

export default configureStore({
  reducer: {
    userStatus: userReducer,
    avatarImage: avatarReducer,
    recentlyPlayed: recentlyPlayedReducer,
  },
});
