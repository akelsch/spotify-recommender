import React, { useEffect } from 'react';
import axios from 'axios';
import StatusCodes from 'http-status-codes';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Button, Columns, Heading } from 'react-bulma-components';
import Layout from '../../Containers/Layout/Layout';
import styles from './Login.module.css';
import { login, logout, selectUserStatus } from '../../State/Slices/UserSlice';

function Login() {
  const onlineStatus = useSelector(selectUserStatus);
  const dispatch = useDispatch();
  useEffect(async () => {
    const response = await axios.get('localhost:8080/me', {
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
    <Layout>
      <Container className={styles.containerLogin}>
        <Columns className={styles.columnsLogin}>
          <Columns.Column>
            <Heading size={3}>Music recommendation reimagined</Heading>
            <Heading size={5}>
              Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
              nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam
              erat, sed diam voluptua. At vero eos et accusam et justo duo
              dolores et ea rebum. Login to get started!
            </Heading>
          </Columns.Column>
          <Columns.Column>
            {onlineStatus ? null : (
              <a href="http://localhost:8080/oauth2/authorization/spotify">
                <Button
                  className={styles.buttonLogin}
                  onClick={async () => dispatch(login(true))}
                >
                  <Heading size={6} className={styles.buttonTextLogin}>
                    Log In with Spotify
                  </Heading>
                </Button>
              </a>
            )}
          </Columns.Column>
        </Columns>
      </Container>
    </Layout>
  );
}
export default Login;
