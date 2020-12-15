import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

export const fetchRecentlyPlayedTracks = createAsyncThunk(
  'recentlyPlayed/fetchRecentlyPlayedTracks',
  async () => {
    const tracks = await SpotifyRecommenderApi.fetchRecentlyPlayedTracks();
    return tracks;
  }
);

const recentlyPlayedSlice = createSlice({
  name: 'recentlyPlayed',
  initialState: {
    tracks: [],
  },
  reducers: {},
  extraReducers: {
    [fetchRecentlyPlayedTracks.fulfilled]: (state, action) => ({
      tracks: action.payload,
    }),
  },
});

export const selectRecentlyPlayedTracks = (state) =>
  state.recentlyPlayed.tracks;
export default recentlyPlayedSlice.reducer;
