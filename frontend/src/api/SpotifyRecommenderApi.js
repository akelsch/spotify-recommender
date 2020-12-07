import axios from 'axios';

export const BACKEND_URL = 'http://localhost:8080';

const client = axios.create({
  baseURL: BACKEND_URL,
  withCredentials: true,
});

async function fetchUser() {
  const response = await client.get('/me');
  return response.data;
}

async function logout() {
  await client.post('/logout');
  return true;
}

async function fetchRecentlyPlayedTracks() {
  const response = await client.get('/api/v1/recently-played');
  return response.data.items;
}

const SpotifyRecommenderApi = {
  fetchUser,
  logout,
  fetchRecentlyPlayedTracks,
};

export default SpotifyRecommenderApi;
