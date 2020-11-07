import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './Pages/Login/Login';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function App() {
  const [isOnline, setOnlineStatus] = useState(true);
  return (
    <div>
      <Router>
        <Switch>
          <Route
            path="/"
            exact
            component={() => (
              <Login
                handleOnlineStatus={setOnlineStatus}
                onlineStatus={isOnline}
              />
            )}
          />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
