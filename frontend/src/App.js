import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';
import PrivateRoute from './app/PrivateRoute';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import RecentlyPlayed from './pages/RecentlyPlayed';
import Discover from './pages/Discover';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Home} />
        <PrivateRoute path="/dashboard" component={Dashboard} />
        <PrivateRoute path="/recently-played" component={RecentlyPlayed} />
        <PrivateRoute path="/discover" component={Discover} />
      </Switch>
    </Router>
  );
}

export default App;
