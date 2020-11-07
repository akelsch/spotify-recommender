import React from 'react';
import PropTypes from 'prop-types';
import Header from './HeaderApp';
import FooterApp from './Footer/FooterApp';
import 'react-bulma-components/dist/react-bulma-components.min.css';

function Layout({ children, handleOnlineStatus, onlineStatus }) {
  return (
    <div>
      <Header
        handleOnlineStatus={handleOnlineStatus}
        onlineStatus={onlineStatus}
      />
      {children}
      <FooterApp />
    </div>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
  handleOnlineStatus: PropTypes.func.isRequired,
  onlineStatus: PropTypes.bool.isRequired,
};

export default Layout;
