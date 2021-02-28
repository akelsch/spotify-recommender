import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Form } from 'react-bulma-components';

const { Field, Label, Control, Select } = Form;

function SettingsForm({ fetchCallback }) {
  const [source, setSource] = useState('top');
  const [timeRange, setTimeRange] = useState('long_term');

  useEffect(() => {
    fetchCallback(source, timeRange);
  }, [fetchCallback, source, timeRange]);

  return (
    <div style={{ width: '360px' }}>
      <Field horizontal>
        <div className="field-label is-normal is-flex-grow-2">
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
        <div className="field-label is-normal is-flex-grow-2">
          <Label>Time Range</Label>
        </div>
        <div className="field-body">
          <Control style={{ width: '100%' }}>
            <Select
              className="is-fullwidth"
              value={timeRange}
              onChange={(e) => setTimeRange(e.target.value)}
              disabled={source !== 'top'}
            >
              <option value="long_term">Long</option>
              <option value="medium_term">Medium</option>
              <option value="short_term">Short</option>
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
