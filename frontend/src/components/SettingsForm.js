import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Form } from 'react-bulma-components';

const { Field, Label, Control, Select } = Form;

function SettingsForm({ fetchCallback }) {
  const [source, setSource] = useState('top');
  const [genre, setGenre] = useState('');

  useEffect(() => {
    fetchCallback(source);
  }, [fetchCallback, source]);

  return (
    <div style={{ width: '360px' }}>
      <Field horizontal>
        <div className="field-label is-normal">
          <Label>Source</Label>
        </div>
        <div className="field-body">
          <Control style={{ width: '100%' }}>
            <Select
              className="is-fullwidth"
              value={source}
              onChange={(e) => setSource(e.target.value)}
            >
              <option value="top">Top Tracks</option>
              <option value="recent">Recently Played Tracks</option>
              <option value="saved">Saved Tracks</option>
              <option value="example">Example Playlist</option>
            </Select>
          </Control>
        </div>
      </Field>
      <Field horizontal>
        <div className="field-label is-normal">
          <Label>Genre</Label>
        </div>
        <div className="field-body">
          <Control style={{ width: '100%' }}>
            <Select
              className="is-fullwidth"
              value={genre}
              onChange={(e) => setGenre(e.target.value)}
            >
              <option>Pop</option>
              <option>Rock</option>
              <option>Electronic</option>
              <option>Hip-Hop</option>
              <option>Classical</option>
            </Select>
          </Control>
        </div>
      </Field>
    </div>
  );
}

SettingsForm.propTypes = {
  fetchCallback: PropTypes.func.isRequired,
};

export default SettingsForm;
