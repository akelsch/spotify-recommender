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

async function fetchDiscoverTracks() {
  const response = await client.get(
    `/api/v1/discover/tracks?page=${Math.floor(Math.random() * 1000)}`
  );
  return response.data.tracks;
}

const SpotifyRecommenderApi = {
  fetchUser,
  logout,
  fetchRecentlyPlayedTracks,
  fetchDiscoverTracks,
};

export default SpotifyRecommenderApi;
