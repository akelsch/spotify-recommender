import React from 'react';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';
import { Box, Media, Content, Button } from 'react-bulma-components';
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
    <Box
      responsive={{
        mobile: {
          display: {
            value: 'block',
          },
        },
        tablet: {
          display: {
            value: 'flex',
          },
        },
        desktop: {
          display: {
            value: 'inline-flex',
            only: true,
          },
        },
        widescreen: {
          display: {
            value: 'inline-block',
          },
        },
      }}
      hide={{
        tablet: {
          hide: true,
          only: true,
        },
        widescreen: {
          hide: true,
        },
      }}
    >
      <Media>
        <Media.Item>
          <Content>
            <p>
              <strong>
                <a href={spotifyLink} target="_blank" rel="noreferrer">
                  {rating.uri}{' '}
                </a>
              </strong>
              <small>{rating.type}</small> <br />
              <p style={{ visibility: 'hidden' }}>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin
                ornare magna eros, eu pellentesque tortor vestibulum ut.
                Maecenas non massa sem. Etiam finibus odio quis
              </p>
            </p>
            <ReactStars
              count={5}
              onChange={handleRatingChange}
              size={24}
              isHalf
              value={rating.rating}
            />
            <Button
              size="small"
              className="button is-danger is-outlined"
              style={{ marginTop: '0.5em' }}
              onClick={() => {
                dispatch(deleteRating(rating.id));
              }}
            >
              delete rating
            </Button>
          </Content>
        </Media.Item>
      </Media>
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
