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

async function fetchRecentlyPlayed(before) {
  const response = await client.get('/api/v1/recently-played', {
    params: {
      before,
    },
  });
  return response.data;
}

async function fetchDiscoverTracks() {
  const response = await client.get(
    `/api/v1/discover/tracks?page=${Math.floor(Math.random() * 1000)}`
  );
  return response.data.tracks;
}

async function fetchDiscoverAlbums() {
  const response = await client.get(
    `/api/v1/discover/albums?page=${Math.floor(Math.random() * 1000)}`
  );
  return response.data.albums;
}

async function fetchDiscoverArtists() {
  const response = await client.get(
    `/api/v1/discover/artists?page=${Math.floor(Math.random() * 1000)}`
  );
  return response.data.artists;
}

const SpotifyRecommenderApi = {
  fetchUser,
  logout,
  fetchRecentlyPlayed,
  fetchDiscoverTracks,
  fetchDiscoverAlbums,
  fetchDiscoverArtists,
};

export default SpotifyRecommenderApi;
