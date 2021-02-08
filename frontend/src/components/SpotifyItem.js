import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlayCircle } from '@fortawesome/free-solid-svg-icons';
import ReactStars from 'react-rating-stars-component';
import { updateRating, createRating } from '../reducers/ratingReducer';
import styles from './SpotifyItem.module.css';

function SpotifyItem({ id, title, name, artist, imageUrl, ratingObject }) {
  const dispatch = useDispatch();
  const [isShown, setShown] = useState(false);
  const [rate, setRate] = useState(ratingObject.rating);
  const [isRated, setIsRated] = useState(Boolean(ratingObject.id));

  let type;
  if (title && artist) {
    type = 'track';
  } else if (artist) {
    type = 'album';
  } else {
    type = 'artist';
  }

  const spotifyLink = `https://open.spotify.com/${type}/${id}`;

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
        onChange={(newRating) => {
          const jsonBody = {
            uri: id,
            type,
            rating: newRating,
          };
          if (isRated) {
            dispatch(
              updateRating({
                id: ratingObject.id,
                ...jsonBody,
              })
            );
          } else {
            dispatch(createRating(jsonBody));
            setIsRated(true);
          }
          setRate(newRating);
        }}
        size={24}
        isHalf
        value={rate}
      />
    </div>
  );
}

SpotifyItem.propTypes = {
  id: PropTypes.string.isRequired,
  title: PropTypes.string,
  name: PropTypes.string,
  artist: PropTypes.string,
  imageUrl: PropTypes.string.isRequired,
  ratingObject: PropTypes.shape({
    id: PropTypes.number,
    rating: PropTypes.number,
  }),
};

SpotifyItem.defaultProps = {
  title: null,
  name: null,
  artist: null,
  ratingObject: { id: 0, rating: 0 },
};

export default SpotifyItem;
