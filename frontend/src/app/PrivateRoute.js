import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Route, Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';

import SpotifyRecommenderApi from '../api/SpotifyRecommenderApi';
import { login, selectUser } from '../reducers/userReducer';

/* eslint-disable react/jsx-props-no-spreading */
function PrivateRoute({ component: Component, ...rest }) {
  const dispatch = useDispatch();
  const user = useSelector(selectUser);

  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      if (user === null) {
        try {
          const newUser = await SpotifyRecommenderApi.fetchUser();
          dispatch(login(newUser));
        } catch (e) {
          // authentication failed, user is not logged in
        }
      }
      setLoading(false);
    };
    fetchData();
  }, [dispatch, user]);

  return (
    <Route
      {...rest}
      render={() => {
        if (!isLoading) {
          if (user !== null) {
            return <Component />;
          }

          return <Redirect to="/" />;
        }

        return <p>Authenticating...</p>;
      }}
    />
  );
}

PrivateRoute.propTypes = {
  component: PropTypes.elementType.isRequired,
};

export default PrivateRoute;
