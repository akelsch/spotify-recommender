import React from 'react';
import { Heading } from 'react-bulma-components';
import Layout from '../../Containers/Layout/Layout';
import styles from './RecentlyPlayed.module.css';

function RecentlyPlayed() {
  return (
    <Layout>
      <Heading size={3} className={styles.headingRecentlyPlayed}>
        RecentlyPlayed
      </Heading>
    </Layout>
  );
}

export default RecentlyPlayed;
