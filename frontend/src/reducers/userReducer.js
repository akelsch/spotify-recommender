import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: {
    value: null,
  },
  reducers: {
    login: (state, action) => ({ value: action.payload }),
    logout: () => ({ value: null }),
  },
});

export const { login, logout } = userSlice.actions;
export const selectUser = (state) => state.user.value;
export const selectUserOnline = (state) => state.user.value !== null;
export const selectUserName = (state) => state.user.value?.display_name;
export const selectUserImage = (state) => state.user.value?.images[0]?.url;
export default userSlice.reducer;
