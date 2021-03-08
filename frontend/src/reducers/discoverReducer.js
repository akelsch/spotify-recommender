import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

export const fetchDiscoverTracks = createAsyncThunk(
  'discover/fetchDiscoverTracks',
  async (queryParams) => {
    const tracks = await SpotifyRecommenderApi.fetchDiscoverTracks(queryParams);
    return tracks;
  }
);

export const fetchDiscoverAlbums = createAsyncThunk(
  'discover/fetchDiscoverAlbums',
  async (queryParams) => {
    const albums = await SpotifyRecommenderApi.fetchDiscoverAlbums(queryParams);
    return albums;
  }
);

export const fetchDiscoverArtists = createAsyncThunk(
  'discover/fetchDiscoverArtists',
  async (queryParams) => {
    const artists = await SpotifyRecommenderApi.fetchDiscoverArtists(
      queryParams
    );
    return artists;
  }
);

const discoverSlice = createSlice({
  name: 'discover',
  initialState: {
    tracks: [],
    albums: [],
    artists: [],
  },
  reducers: {},
  extraReducers: {
    [fetchDiscoverTracks.fulfilled]: (state, action) => {
      state.tracks = action.payload;
    },
    [fetchDiscoverAlbums.fulfilled]: (state, action) => {
      state.albums = action.payload;
    },
    [fetchDiscoverArtists.fulfilled]: (state, action) => {
      state.artists = action.payload;
    },
  },
});

export const selectDiscoverTracks = (state) => state.discover.tracks;
export const selectDiscoverAlbums = (state) => state.discover.albums;
export const selectDiscoverArtists = (state) => state.discover.artists;
export default discoverSlice.reducer;
