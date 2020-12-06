import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const getRecentlyPlayedSongs = async () => {
  const response = await axios.get(`${BASE_URL}/api/v1/recently-played`, {
    withCredentials: true,
  });
  return response.data.items;
};

const getUserInformation = async () => {
  const response = await axios.get(`${BASE_URL}/me`, {
    withCredentials: true,
  });
  const imgUrl = response.data.images.length ? response.data.images[0].url : '';
  const userName = response.data.display_name;
  return { userName, imgUrl };
};

const SpotifyRecommenderApi = {
  getRecentlyPlayedSongs,
  getUserInformation,
};

export default SpotifyRecommenderApi;
