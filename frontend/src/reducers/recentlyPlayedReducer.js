import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

export const fetchRecentlyPlayed = createAsyncThunk(
  'recentlyPlayed/fetchRecentlyPlayed',
  async () => {
    const recentlyPlayed = await SpotifyRecommenderApi.fetchRecentlyPlayed();
    return recentlyPlayed;
  }
);

export const updateRecentlyPlayed = createAsyncThunk(
  'recentlyPlayed/updateRecentlyPlayed',
  async (before) => {
    const recentlyPlayed = await SpotifyRecommenderApi.fetchRecentlyPlayed(
      before
    );
    return recentlyPlayed;
  }
);

const recentlyPlayedSlice = createSlice({
  name: 'recentlyPlayed',
  initialState: {
    tracks: [],
    cursors: {},
  },
  reducers: {},
  extraReducers: {
    [fetchRecentlyPlayed.fulfilled]: (state, action) => ({
      tracks: action.payload.items,
      cursors: action.payload.cursors,
    }),
    [updateRecentlyPlayed.fulfilled]: (state, action) => ({
      tracks: [...state.tracks, ...action.payload.items],
      cursors: action.payload.cursors,
    }),
  },
});

export const selectRecentlyPlayedTracks = (state) =>
  state.recentlyPlayed.tracks;
export const selectRecentlyPlayedBeforeCursor = (state) =>
  state.recentlyPlayed.cursors?.before;
export default recentlyPlayedSlice.reducer;
