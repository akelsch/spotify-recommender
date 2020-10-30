import React from 'react';
import { Container, Button, Columns, Heading } from 'react-bulma-components';
import Layout from '../../Layouts/Layout/Layout';
import styles from './Login.module.css';

function Login() {
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
            <a href="http://localhost:8080/oauth2/authorization/spotify">
              <Button className={styles.buttonLogin}>
                <Heading size={6} className={styles.buttonTextLogin}>
                  Log In with Spotify
                </Heading>
              </Button>
            </a>
          </Columns.Column>
        </Columns>
      </Container>
    </Layout>
  );
}
export default Login;
