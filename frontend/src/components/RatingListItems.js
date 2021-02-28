import React from 'react';
import PropTypes from 'prop-types';
import RatingItem from './RatingItem';

function RatingListItems({ ratings }) {
  const components = ratings.map((rating) => (
    <RatingItem key={rating.played_at} rating={rating} ratingId={rating.id} />
  ));

  return <div className="is-flex is-flex-wrap-wrap">{components}</div>;
}

RatingListItems.propTypes = {
  ratings: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      user_id: PropTypes.string,
      uri: PropTypes.string,
      type: PropTypes.string,
      rating: PropTypes.number,
    })
  ).isRequired,
};

export default RatingListItems;
