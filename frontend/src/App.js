import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { login } from './State/Slices/UserSlice';
import { loadImage } from './State/Slices/AvatarSlice';
import { getRecentlyPlayedSongs } from './State/Slices/RecentlyPlayedSlice';
import SpotifyRecommenderApi from './Api/SpotifyRecommenderApi';
import Login from './Pages/Login/Login';
import RecentlyPlayed from './Pages/RecentlyPlayed/RecentlyPlayed';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const recentlyPlayedSongs = await SpotifyRecommenderApi.getRecentlyPlayedSongs();
        const imgUrl = await SpotifyRecommenderApi.getUserImage();
        dispatch(login(true));
        dispatch(loadImage(imgUrl));
        dispatch(getRecentlyPlayedSongs(recentlyPlayedSongs));
      } catch (error) {
        if (window.location.pathname !== '/') {
          window.location.replace('/');
        }
      }
    };

    fetchUserData();
  }, []);

  return (
    <div>
      <Router>
        <Switch>
          <Route path="/" exact component={() => <Login />} />
          <Route path="/recently-played" component={() => <RecentlyPlayed />} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
