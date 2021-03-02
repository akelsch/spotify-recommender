import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle } from '@fortawesome/free-solid-svg-icons';
import ReactStars from 'react-rating-stars-component';
import { updateRating, createRating } from '../reducers/ratingReducer';

import styles from './SpotifyItem.module.css';

function SpotifyItem({ item, rating }) {
  const { id: itemId, title, name, artist, image_url: imageUrl } = item;

  const dispatch = useDispatch(); // TODO remove and pass callback instead?
  const [isShown, setShown] = useState(false);

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
      <ReactStars
        count={5}
        onChange={handleRatingChange}
        size={24}
        isHalf
        value={rating.rating}
      />
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
