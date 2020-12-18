/* eslint-disable */
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InfiniteScroll from 'react-infinite-scroll-component';
import SpotifyItem from './SpotifyItem';
import { fetchRecentlyPlayedTracks } from '../reducers/recentlyPlayedReducer';

function CoverGrid({ tracks }) {
  const [items, setItems] = useState(tracks);

  const fetchMoreData =  () => {
    console.log("Hello1")
    setTimeout(()=> {
      setItems(items.concat([...tracks]));

    },500)    
   
  };

  return (
    <div id="scrollableDiv" className="is-flex is-flex-wrap-wrap">
      <InfiniteScroll
        dataLength={items.length}
        next={fetchMoreData}
        className="is-flex is-flex-wrap-wrap"
        hasMore
        loader={""}
     
      >
        {items.map(({ id, title, artist, image_url, played_at }, index) => (
          <SpotifyItem
            key={played_at + index}
            id={id}
            title={title}
            artist={artist}
            imageUrl={image_url}
          />
        ))}
      </InfiniteScroll>
    </div>
  );
}

CoverGrid.propTypes = {
  tracks: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      title: PropTypes.string.isRequired,
      artist: PropTypes.string.isRequired,
      album: PropTypes.string.isRequired,
      image_url: PropTypes.string.isRequired,
      played_at: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default CoverGrid;
