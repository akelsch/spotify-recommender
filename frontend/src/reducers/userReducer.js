import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

export const login = createAsyncThunk('user/login', async () => {
  const user = await SpotifyRecommenderApi.fetchUser();
  return user;
});

const userSlice = createSlice({
  name: 'user',
  initialState: {
    value: null,
  },
  reducers: {
    logout: (state) => {
      state.value = null;
    },
  },
  extraReducers: {
    [login.fulfilled]: (state, action) => {
      state.value = action.payload;
    },
    [login.rejected]: (state) => {
      state.value = null;
    },
  },
});

export const { logout } = userSlice.actions;
export const selectUser = (state) => state.user.value;
export const selectUserName = (state) => state.user.value.display_name;
export const selectUserImage = (state) => state.user.value.images[0]?.url;
export default userSlice.reducer;
