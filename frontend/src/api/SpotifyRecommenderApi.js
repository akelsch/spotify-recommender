import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const getRecentlyPlayedSongs = async () => {
  const response = await axios.get(`${BASE_URL}/api/v1/recently-played`, {
    withCredentials: true,
  });
  return response.data.items;
};

const getUserImage = async () => {
  const response = await axios.get(`${BASE_URL}/me`, {
    withCredentials: true,
  });
  const imgUrl = response.data.images.length
    ? response.data.images[0].url
    : 'https://via.placeholder.com/600/771796';
  return imgUrl;
};

const getUserName = async () => {
  const response = await axios.get(`${BASE_URL}/me`, {
    withCredentials: true,
  });
  const userName = response.data.display_name;

  return userName;
};

const SpotifyRecommenderApi = {
  getRecentlyPlayedSongs,
  getUserImage,
  getUserName,
};

export default SpotifyRecommenderApi;
