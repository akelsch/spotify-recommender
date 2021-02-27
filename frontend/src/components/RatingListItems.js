import React from 'react';
import PropTypes from 'prop-types';
import RatingItem from './RatingItem';

function RatingListItems({ items, ratings }) {
  const components = items.map((item) => {
    const rating = ratings.find(({ uri }) => uri === item.id);
    return rating ? (
      <RatingItem key={item.played_at} item={item} ratingId={rating.id} />
    ) : null;
  });
  return <div className="is-flex is-flex-wrap-wrap">{components}</div>;
}

RatingListItems.propTypes = {
  items: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      title: PropTypes.string.isRequired,
      artist: PropTypes.string.isRequired,
      album: PropTypes.string.isRequired,
      image_url: PropTypes.string.isRequired,
      played_at: PropTypes.string.isRequired,
    })
  ).isRequired,

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
