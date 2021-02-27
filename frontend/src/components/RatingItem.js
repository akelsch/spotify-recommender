import React from 'react';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';
import { Box, Media, Image, Content, Button } from 'react-bulma-components';
import { deleteRating } from '../reducers/ratingReducer';

function RatingItem({ item, ratingId }) {
  const dispatch = useDispatch();
  console.log(ratingId);
  const { title, name, artist, image_url: imageUrl } = item;

  let type;
  if (title && artist) {
    type = 'track';
  } else if (artist) {
    type = 'album';
  } else {
    type = 'artist';
  }

  let imageAlt;
  if (type === 'track') {
    imageAlt = `Cover of ${title} by ${artist}`;
  } else if (type === 'album') {
    imageAlt = `Cover of ${name} by ${artist}`;
  } else {
    imageAlt = `Cover of ${name}`;
  }
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
        <Media.Item renderAs="figure" position="left">
          <Image size={64} alt={imageAlt} src={imageUrl} />
        </Media.Item>
        <Media.Item>
          <Content>
            <p>
              <strong>{title}</strong> <small>{artist}</small> <br />
              <p style={{ visibility: 'hidden' }}>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin
                ornare magna eros, eu pellentesque tortor vestibulum ut.
                Maecenas non massa sem. Etiam finibus odio quis
              </p>
            </p>
            <Button
              size="small"
              className="button is-danger is-outlined"
              style={{ marginTop: '0.5em' }}
              onClick={() => {
                dispatch(deleteRating(ratingId));
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
  item: PropTypes.shape({
    title: PropTypes.string,
    name: PropTypes.string,
    artist: PropTypes.string,
    album: PropTypes.string,
    image_url: PropTypes.string.isRequired,
    played_at: PropTypes.string,
  }),
  ratingId: PropTypes.number,
};
RatingItem.defaultProps = {
  item: null,
  ratingId: 0,
};

export default RatingItem;
