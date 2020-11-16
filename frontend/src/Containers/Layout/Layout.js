import React from 'react';
import PropTypes from 'prop-types';
import { Container } from 'react-bulma-components';
import Header from './HeaderApp';
import Footer from './Footer/FooterApp';

function Layout({ children }) {
  return (
    <>
      <header>
        <Header />
      </header>
      <main>
        <Container className="py-6">{children}</Container>
      </main>
      <footer>
        <Footer />
      </footer>
    </>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
