import React from 'react';
import { Heading } from 'react-bulma-components';

import Layout from './layout/Layout';

function Discover() {
  return (
    <Layout>
      <Heading>Discover</Heading>
      <Heading subtitle renderAs="h2">
        Here&apos;s what we recommend you checking out
      </Heading>
    </Layout>
  );
}

export default Discover;
