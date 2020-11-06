import React from 'react';
import PropTypes from 'prop-types';
import Header from './HeaderApp';
import FooterApp from './Footer/FooterApp';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function Layout({ children, onlineStatus }) {
  return (
    <div>
      <Header onlineStatus={onlineStatus} />
      {children}
      <FooterApp />
    </div>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
  onlineStatus: PropTypes.bool.isRequired,
};

export default Layout;
