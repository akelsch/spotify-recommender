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

async function fetchRatings() {
  const response = await client.get('/api/v1/ratings');
  return response.data;
}

async function createRating(rating) {
  const response = await client.post('/api/v1/ratings', rating);
  return response.data;
}

async function updateRating(rating) {
  const response = await client.put(`/api/v1/ratings/${rating.id}`, rating);
  return response.data;
}

const SpotifyRecommenderApi = {
  fetchUser,
  logout,
  fetchRecentlyPlayed,
  fetchDiscoverTracks,
  fetchDiscoverAlbums,
  fetchDiscoverArtists,
  fetchRatings,
  createRating,
  updateRating,
};

export default SpotifyRecommenderApi;
