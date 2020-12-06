import { combineReducers } from '@reduxjs/toolkit';

import newUserReducer from './newUserSlice';
import recentlyPlayedReducer from './RecentlyPlayedSlice';

export default combineReducers({
  user: newUserReducer,
  recentlyPlayed: recentlyPlayedReducer,
});
