import { createSlice } from '@reduxjs/toolkit';

export const avatarSlice = createSlice({
  name: 'avatarImage',
  initialState: {
    value: '',
  },
  reducers: {
    loadImage: (state, url) => {
      state.value = url.payload;
    },
  },
});

export const { loadImage } = avatarSlice.actions;
export const selectAvatarImage = (state) => state.avatarImage.value;
export default avatarSlice.reducer;
