import React from 'react';
import PropTypes from 'prop-types';
import { Heading } from 'react-bulma-components';

import styles from './Headline.module.css';

function Headline({ title, subtitle }) {
  return (
    <div className={styles.headline}>
      <Heading>{title}</Heading>
      <Heading subtitle renderAs="h2">
        {subtitle}
      </Heading>
    </div>
  );
}

Headline.propTypes = {
  title: PropTypes.string.isRequired,
  subtitle: PropTypes.string.isRequired,
};

export default Headline;
