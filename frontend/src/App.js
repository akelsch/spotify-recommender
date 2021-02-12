import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';
import PrivateRoute from './app/PrivateRoute';
import Home from './routes/Home';
import Dashboard from './routes/Dashboard';
import RecentlyPlayed from './routes/RecentlyPlayed';
import Discover from './routes/Discover';
import Ratings from './routes/Ratings';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Home} />
        <PrivateRoute path="/dashboard" component={Dashboard} />
        <PrivateRoute path="/recently-played" component={RecentlyPlayed} />
        <PrivateRoute path="/discover" component={Discover} />
        <PrivateRoute path="/ratings" component={Ratings} />
      </Switch>
    </Router>
  );
}

export default App;
