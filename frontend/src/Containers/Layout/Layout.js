import React from 'react';
import PropTypes from 'prop-types';
import { Container } from 'react-bulma-components';
import Header from './HeaderApp';
import FooterApp from './Footer/FooterApp';
import styles from './Layout.module.css';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function Layout({ children }) {
  return (
    <div>
      <Header />
      <Container className={styles.layoutContainer}>{children}</Container>
      <FooterApp />
    </div>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
