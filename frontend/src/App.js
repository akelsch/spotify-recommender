import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';
import { login, selectUser } from './state/slices/newUserSlice';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import RecentlyPlayed from './pages/RecentlyPlayed';
import Discover from './pages/Discover';

function App() {
  const dispatch = useDispatch();
  const user = useSelector(selectUser);

  useEffect(() => {
    if (user === null) {
      dispatch(login());
    }
  });

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
