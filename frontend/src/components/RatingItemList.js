import React from 'react';
import PropTypes from 'prop-types';
import RatingItem from './RatingItem';

function RatingItemList({ ratings }) {
  return (
    <div>
      {[...ratings]
        .sort((x, y) => y.id - x.id)
        .map((rating) => (
          <RatingItem key={rating.id} rating={rating} />
        ))}
    </div>
  );
}

RatingItemList.propTypes = {
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

export default RatingItemList;
