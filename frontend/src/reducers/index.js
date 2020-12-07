import { combineReducers } from '@reduxjs/toolkit';

import userReducer from './userReducer';
import recentlyPlayedReducer from './recentlyPlayedReducer';
import discoverReducer from './discoverReducer';

export default combineReducers({
  user: userReducer,
  recentlyPlayed: recentlyPlayedReducer,
  discover: discoverReducer,
});
