import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

async function fetchUser() {
  const response = await axios.get(`${BASE_URL}/me`, {
    withCredentials: true,
  });
  return response.data;
}

async function fetchRecentlyPlayedTracks() {
  const response = await axios.get(`${BASE_URL}/api/v1/recently-played`, {
    withCredentials: true,
  });
  return response.data.items;
}

const SpotifyRecommenderApi = {
  fetchUser,
  fetchRecentlyPlayedTracks,
};

export default SpotifyRecommenderApi;
