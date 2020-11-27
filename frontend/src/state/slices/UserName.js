import { createSlice } from '@reduxjs/toolkit';

export const userNameSlice = createSlice({
  name: 'userName',
  initialState: {
    value: '',
  },
  reducers: {
    setUserName: (state, userName) => {
      state.value = userName.payload;
    },
  },
});

export const { setUserName } = userNameSlice.actions;
export const selectUserName = (state) => state.userName.value;
export default userNameSlice.reducer;
