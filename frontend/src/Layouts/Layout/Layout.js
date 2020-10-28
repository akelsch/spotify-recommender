import React from 'react';
import PropTypes from 'prop-types';
import Header from './HeaderApp';
import FooterApp from './Footer/FooterApp';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function Layout({ children }) {
  return (
    <div>
      <Header />
      {children}
      <FooterApp />
    </div>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
