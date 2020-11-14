import React, { useEffect } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { login } from './State/Slices/UserSlice';
import { loadImage } from './State/Slices/AvatarSlice';
import Login from './Pages/Login/Login';
import RecentlyPlayed from './Pages/RecentlyPlayed/RecentlyPlayed';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    const fetchUserStatusAndImage = async () => {
      try {
        const response = await axios.get('http://localhost:8080/me', {
          withCredentials: true,
        });
        const imgUrl = response.data.images.length
          ? response.data.images[0].url
          : 'https://via.placeholder.com/600/771796';
        dispatch(login(true));
        dispatch(loadImage(imgUrl));
      } catch (error) {
        if (window.location.pathname !== '/') {
          window.location.replace('/');
        }
      }
    };

    fetchUserStatusAndImage();
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
