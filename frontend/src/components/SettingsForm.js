import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Form, Columns } from 'react-bulma-components';

const { Field, Label, Control, Select, Checkbox } = Form;

function SettingsForm({ updateCallback }) {
  const [source, setSource] = useState('top');
  const [timeRange, setTimeRange] = useState('long_term');
  const [filter, setFilter] = useState(false);

  useEffect(() => {
    if (source === 'top') {
      updateCallback({ source, time_range: timeRange, filter });
    } else {
      updateCallback({ source, filter });
    }
  }, [updateCallback, source, timeRange, filter]);

  return (
    <div style={{ maxWidth: '500px' }}>
      <Columns>
        <Columns.Column>
          <Field>
            <Label>Source</Label>
            <Control>
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
          </Field>
        </Columns.Column>
        <Columns.Column>
          <Field>
            <Label>Time Range</Label>
            <Control>
              <Select
                className="is-fullwidth"
                value={timeRange}
                onChange={(e) => {
                  console.log(e);
                  setTimeRange(e.target.value);
                }}
                disabled={source !== 'top'}
              >
                <option value="long_term">Long</option>
                <option value="medium_term">Medium</option>
                <option value="short_term">Short</option>
              </Select>
            </Control>
          </Field>
        </Columns.Column>
      </Columns>
      <Columns>
        <Columns.Column>
          <Field>
            <Control>
              <Checkbox
                onChange={(e) => setFilter(e.target.checked)}
                checked={filter}
              >
                {' '}
                Filter Known Tracks
              </Checkbox>
            </Control>
          </Field>
        </Columns.Column>
      </Columns>
    </div>
  );
}

SettingsForm.propTypes = {
  updateCallback: PropTypes.func.isRequired,
};

export default SettingsForm;
