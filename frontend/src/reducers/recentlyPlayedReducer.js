import { createSlice } from '@reduxjs/toolkit';

const recentlyPlayedSlice = createSlice({
  name: 'recentlyPlayed',
  initialState: {
    value: [],
  },
  reducers: {
    getRecentlyPlayedTracks: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { getRecentlyPlayedTracks } = recentlyPlayedSlice.actions;
export const selectRecentlyPlayedTracks = (state) => state.recentlyPlayed.value;
export default recentlyPlayedSlice.reducer;
