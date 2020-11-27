import React from 'react';
import { Heading } from 'react-bulma-components';
import { useSelector } from 'react-redux';
import { selectUserName } from '../state/slices/UserName';

import Layout from './layout/Layout';

function Dashboard() {
  const userName = useSelector(selectUserName);
  const text = `Welcome back, ${userName}! How is it going`;
  return (
    <Layout>
      <Heading>Dashboard</Heading>
      <Heading subtitle renderAs="h2">
        {text}
      </Heading>

      <Heading>Recently Played</Heading>

      <Heading>Discover</Heading>
    </Layout>
  );
}

export default Dashboard;
