import { createSlice } from '@reduxjs/toolkit';

const recentlyPlayedSlice = createSlice({
  name: 'recentlyPlayed',
  initialState: {
    value: [],
  },
  reducers: {
    setRecentlyPlayedTracks: (state, action) => ({ value: action.payload }),
  },
});

export const { setRecentlyPlayedTracks } = recentlyPlayedSlice.actions;
export const selectRecentlyPlayedTracks = (state) => state.recentlyPlayed.value;
export default recentlyPlayedSlice.reducer;
