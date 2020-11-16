import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'userStatus',
  initialState: {
    value: false,
  },
  reducers: {
    login: (state) => {
      state.value = true;
    },
    logout: (state) => {
      state.value = false;
    },
  },
});

export const { login, logout } = userSlice.actions;
export const selectUserStatus = (state) => state.userStatus.value;
export default userSlice.reducer;
