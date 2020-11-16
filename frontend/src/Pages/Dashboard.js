import React from 'react';
import { Heading } from 'react-bulma-components';

import Layout from './layout/Layout';

function Dashboard() {
  return (
    <Layout>
      <Heading>Dashboard</Heading>
      <Heading subtitle renderAs="h2">
        Welcome back, Elon! How is it going?
      </Heading>

      <Heading>Recently Played</Heading>

      <Heading>Discover</Heading>
    </Layout>
  );
}

export default Dashboard;
