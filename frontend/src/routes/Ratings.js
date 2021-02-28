import React from 'react';
import { useSelector } from 'react-redux';

import { selectRatings } from '../reducers/ratingReducer';
import Layout from './layout/Layout';
import RatingListItem from '../components/RatingListItems';
import Headline from '../components/common/Headline';

function Ratings() {
  const ratings = useSelector(selectRatings);

  return (
    <Layout>
      <Headline
        title="Ratings"
        subtitle="Review your rated tracks, albums and artists"
      />
      <RatingListItem ratings={ratings} />
    </Layout>
  );
}

export default Ratings;
