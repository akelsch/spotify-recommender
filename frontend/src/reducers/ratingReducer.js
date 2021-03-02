import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';

export const fetchRatings = createAsyncThunk(
  'rating/fetchRatings',
  async () => {
    const ratings = await SpotifyRecommenderApi.fetchRatings();
    return ratings;
  }
);

export const createRating = createAsyncThunk(
  'rating/createRating',
  async (ratingObject) => {
    const rating = await SpotifyRecommenderApi.createRating(ratingObject);
    return rating;
  }
);

export const updateRating = createAsyncThunk(
  'rating/updateRating',
  async (ratingObject) => {
    const rating = await SpotifyRecommenderApi.updateRating(ratingObject);
    return rating;
  }
);

export const deleteRating = createAsyncThunk(
  'rating/deleteRating',
  async (ratingId) => {
    await SpotifyRecommenderApi.deleteRating(ratingId);
    return ratingId;
  }
);

const ratingSlice = createSlice({
  name: 'rating',
  initialState: {
    ratings: [],
  },
  reducers: {},
  extraReducers: {
    [fetchRatings.fulfilled]: (state, action) => {
      state.ratings = action.payload;
    },
    [createRating.fulfilled]: (state, action) => {
      state.ratings.push(action.payload);
    },
    [updateRating.fulfilled]: (state, action) => {
      const index = state.ratings.findIndex(
        ({ id }) => id === action.payload.id
      );
      state.ratings[index] = action.payload;
    },
    [deleteRating.fulfilled]: (state, action) => {
      state.ratings = state.ratings.filter(({ id }) => id !== action.payload);
    },
  },
});

export const selectRatings = (state) => state.rating.ratings;
export default ratingSlice.reducer;
