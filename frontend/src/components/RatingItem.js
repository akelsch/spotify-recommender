import React from 'react';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';
import { Box, Button } from 'react-bulma-components';
import ReactStars from 'react-rating-stars-component';
import { deleteRating, updateRating } from '../reducers/ratingReducer';

function RatingItem({ rating }) {
  const dispatch = useDispatch();
  const spotifyLink = `https://open.spotify.com/${rating.type}/${rating.uri}`;

  const handleRatingChange = (newRating) =>
    dispatch(
      updateRating({
        id: rating.id,
        uri: rating.uri,
        type: rating.type,
        rating: newRating,
      })
    );

  return (
    <Box>
      <p>
        <strong>
          <a href={spotifyLink} target="_blank" rel="noreferrer">
            {rating.uri}
          </a>
        </strong>{' '}
        <small>{rating.type}</small>
        <br />
      </p>
      <ReactStars
        count={5}
        onChange={handleRatingChange}
        size={24}
        isHalf
        value={rating.rating}
      />
      <Button
        className="mt-2"
        color="danger"
        size="small"
        outlined
        onClick={() => {
          dispatch(deleteRating(rating.id));
        }}
      >
        Delete
      </Button>
    </Box>
  );
}

RatingItem.propTypes = {
  rating: PropTypes.shape({
    id: PropTypes.number,
    user_id: PropTypes.string,
    uri: PropTypes.string,
    type: PropTypes.string,
    rating: PropTypes.number,
  }),
};

RatingItem.defaultProps = {
  rating: {},
};

export default RatingItem;
