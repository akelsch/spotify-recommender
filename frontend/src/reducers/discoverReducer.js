import { createSlice } from '@reduxjs/toolkit';

const discoverSlice = createSlice({
  name: 'discover',
  initialState: {
    value: [],
  },
  reducers: {
    setDiscoverTracks: (state, action) => ({ value: action.payload }),
  },
});

export const { setDiscoverTracks } = discoverSlice.actions;
export const selectDiscoverTracks = (state) => state.discover.value;
export default discoverSlice.reducer;
