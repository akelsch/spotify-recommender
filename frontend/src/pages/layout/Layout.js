import React from 'react';
import PropTypes from 'prop-types';
import { Container } from 'react-bulma-components';

import Header from './Header';
import Footer from './Footer';

function Layout({ children }) {
  return (
    <>
      <Header />
      <Container className="py-6" renderAs="main">
        {children}
      </Container>
      <Footer />
    </>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
