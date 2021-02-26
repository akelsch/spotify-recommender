import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Container, Button, Columns, Icon } from 'react-bulma-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle, faTimes } from '@fortawesome/free-solid-svg-icons';
import ReactStars from 'react-rating-stars-component';
import { useDispatch } from 'react-redux';
import {
  updateRating,
  createRating,
  deleteRating,
} from '../reducers/ratingReducer';

import styles from './SpotifyItem.module.css';

function SpotifyItem({ item, rating }) {
  const { id: itemId, title, name, artist, image_url: imageUrl } = item;

  const dispatch = useDispatch(); // TODO remove and pass callback instead?
  const [isShown, setShown] = useState(false);
  const [ratingValue, setRatingValue] = useState(rating.id ? rating.rating : 0);
  console.log(rating);

  let type;
  if (title && artist) {
    type = 'track';
  } else if (artist) {
    type = 'album';
  } else {
    type = 'artist';
  }

  const spotifyLink = `https://open.spotify.com/${type}/${itemId}`;

  let imageAlt;
  let textComponents;
  if (type === 'track') {
    imageAlt = `Cover of ${title} by ${artist}`;
    textComponents = (
      <>
        <p>{title}</p>
        <p>{artist}</p>
      </>
    );
  } else if (type === 'album') {
    imageAlt = `Cover of ${name} by ${artist}`;
    textComponents = (
      <>
        <p>{name}</p>
        <p>{artist}</p>
      </>
    );
  } else {
    imageAlt = `Cover of ${name}`;
    textComponents = (
      <>
        <p>{name}</p>
      </>
    );
  }

  const handleRatingChange = (newRating) => {
    const payload = {
      id: rating.id,
      uri: itemId,
      type,
      rating: newRating,
    };

    if (payload.id) {
      dispatch(updateRating(payload));
    } else {
      dispatch(createRating(payload));
    }
  };

  return (
    <div className={styles.itemContainer}>
      <div
        className={styles.coverContainer}
        onMouseEnter={() => setShown(!isShown)}
        onMouseLeave={() => setShown(!isShown)}
      >
        {isShown && (
          <FontAwesomeIcon
            className={styles.coverPlayButton}
            icon={faPlayCircle}
            onClick={() => window.open(spotifyLink)}
          />
        )}
        <img src={imageUrl} alt={imageAlt} />
      </div>
      {textComponents}
      <Container>
        <Columns>
          <Columns.Column className="column is-three-quarters">
            <ReactStars
              count={5}
              onChange={handleRatingChange}
              size={24}
              isHalf
              value={ratingValue}
            />
          </Columns.Column>
          <Columns.Column className="column">
            <Button
              size="small"
              className="button is-danger is-outlined"
              style={{ marginTop: '0.5em' }}
              onClick={() => {
                dispatch(deleteRating(rating.id));
                setRatingValue(0);
              }}
            >
              <Icon>
                <FontAwesomeIcon icon={faTimes} />
              </Icon>
            </Button>
          </Columns.Column>
        </Columns>
      </Container>
    </div>
  );
}

SpotifyItem.propTypes = {
  item: PropTypes.shape({
    id: PropTypes.string.isRequired,
    title: PropTypes.string,
    name: PropTypes.string,
    artist: PropTypes.string,
    album: PropTypes.string,
    image_url: PropTypes.string.isRequired,
    played_at: PropTypes.string,
  }),
  rating: PropTypes.shape({
    id: PropTypes.number,
    user_id: PropTypes.string,
    uri: PropTypes.string,
    type: PropTypes.string,
    rating: PropTypes.number,
  }),
};

SpotifyItem.defaultProps = {
  item: null,
  rating: {},
};

export default SpotifyItem;
