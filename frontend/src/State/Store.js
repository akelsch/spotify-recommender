import { configureStore } from '@reduxjs/toolkit';
// eslint-disable-next-line import/no-unresolved
import userReducer from './Slices/UserSlice';

export default configureStore({
  reducer: {
    userStatus: userReducer,
  },
});
