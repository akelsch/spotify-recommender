import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';
import { login } from './state/slices/UserSlice';
import { loadImage } from './state/slices/AvatarSlice';
import { getRecentlyPlayedSongs } from './state/slices/RecentlyPlayedSlice';
import { setUserName } from './state/slices/UserName';
import SpotifyRecommenderApi from './api/SpotifyRecommenderApi';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import RecentlyPlayed from './pages/RecentlyPlayed';
import Discover from './pages/Discover';

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const recentlyPlayedSongs = await SpotifyRecommenderApi.getRecentlyPlayedSongs();
        const imgUrl = await SpotifyRecommenderApi.getUserImage();
        const userName = await SpotifyRecommenderApi.getUserName();
        dispatch(login(true));
        dispatch(loadImage(imgUrl));
        dispatch(setUserName(userName));
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
    <Router>
      <Switch>
        <Route path="/" exact component={() => <Home />} />
        <Route path="/dashboard" component={() => <Dashboard />} />
        <Route path="/recently-played" component={() => <RecentlyPlayed />} />
        <Route path="/discover" component={() => <Discover />} />
      </Switch>
    </Router>
  );
}

export default App;
