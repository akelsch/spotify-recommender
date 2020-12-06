import { combineReducers } from '@reduxjs/toolkit';

import userReducer from './userReducer';
import recentlyPlayedReducer from './recentlyPlayedReducer';

export default combineReducers({
  user: userReducer,
  recentlyPlayed: recentlyPlayedReducer,
});
