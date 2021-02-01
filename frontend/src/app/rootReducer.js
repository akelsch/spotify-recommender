import { combineReducers } from '@reduxjs/toolkit';

import userReducer from '../reducers/userReducer';
import recentlyPlayedReducer from '../reducers/recentlyPlayedReducer';
import discoverReducer from '../reducers/discoverReducer';
import ratingReducer from '../reducers/ratingReducer';

const rootReducer = combineReducers({
  user: userReducer,
  recentlyPlayed: recentlyPlayedReducer,
  discover: discoverReducer,
  rating: ratingReducer,
});

export default rootReducer;
