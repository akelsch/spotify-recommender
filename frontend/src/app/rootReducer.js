import { combineReducers } from '@reduxjs/toolkit';

import userReducer from '../reducers/userReducer';
import recentlyPlayedReducer from '../reducers/recentlyPlayedReducer';
import discoverReducer from '../reducers/discoverReducer';

const rootReducer = combineReducers({
  user: userReducer,
  recentlyPlayed: recentlyPlayedReducer,
  discover: discoverReducer,
});

export default rootReducer;
