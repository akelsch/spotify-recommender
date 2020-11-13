import React, { useEffect } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import StatusCodes from 'http-status-codes';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { login, logout } from './State/Slices/UserSlice';
import Login from './Pages/Login/Login';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function App() {
  const dispatch = useDispatch();
  useEffect(async () => {
    const response = await axios.get('http://localhost:8080/me', {
      withCredentials: true,
    });
    console.log(response.data);
    if (response.status === StatusCodes.UNAUTHORIZED) {
      dispatch(logout(false));
    } else {
      dispatch(login(true));
    }
  }, []);
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/" exact component={() => <Login />} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
