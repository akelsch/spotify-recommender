import axios from 'axios';

const BACKEND_URL = 'http://localhost:8080';
export const LOGIN_URL = `${BACKEND_URL}/oauth2/authorization/spotify`;

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

async function fetchDiscoverTracks(source, timeRange) {
  const response = await client.get('/api/v1/discover/tracks', {
    params: {
      source,
      time_range: timeRange,
    },
  });
  return response.data.tracks;
}

async function fetchDiscoverAlbums(source, timeRange) {
  const response = await client.get('/api/v1/discover/albums', {
    params: {
      source,
      time_range: timeRange,
    },
  });
  return response.data.albums;
}

async function fetchDiscoverArtists(source, timeRange) {
  const response = await client.get('/api/v1/discover/artists', {
    params: {
      source,
      time_range: timeRange,
    },
  });
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

async function deleteRating(ratingId) {
  await client.delete(`/api/v1/ratings/${ratingId}`);
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
  deleteRating,
};

export default SpotifyRecommenderApi;
