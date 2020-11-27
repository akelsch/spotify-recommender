import { configureStore } from '@reduxjs/toolkit';
import userAvatarReducer from './slices/UserSlice';
import avatarReducer from './slices/AvatarSlice';
import recentlyPlayedReducer from './slices/RecentlyPlayedSlice';
import userNameReducer from './slices/UserNameSlice';

export default configureStore({
  reducer: {
    userStatus: userAvatarReducer,
    avatarImage: avatarReducer,
    recentlyPlayed: recentlyPlayedReducer,
    userName: userNameReducer,
  },
});
