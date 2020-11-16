import { createSlice } from '@reduxjs/toolkit';

export const recentlyPlayedSlice = createSlice({
  name: 'recentlyPlayed',
  initialState: {
    value: [],
  },
  reducers: {
    getRecentlyPlayedSongs: (state, songs) => {
      state.value = songs.payload;
    },
  },
});

export const { getRecentlyPlayedSongs } = recentlyPlayedSlice.actions;
export const selectRecentlyPlayedSongs = (state) => state.recentlyPlayed.value;
export default recentlyPlayedSlice.reducer;
