import React, { useState } from 'react';
import { Form } from 'react-bulma-components';

const { Field, Label, Control, Select } = Form;

function SettingsForm() {
  const [source, setSource] = useState('');
  const [genre, setGenre] = useState('');

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
              <option>Top Tracks</option>
              <option>Recently Played Tracks</option>
              <option>Saved Tracks</option>
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

export default SettingsForm;
