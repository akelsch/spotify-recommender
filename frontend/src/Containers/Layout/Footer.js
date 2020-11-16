import React from 'react';
import { Footer as BulmaFooter, Content } from 'react-bulma-components';

function Footer() {
  return (
    <BulmaFooter renderAs="footer">
      <Content className="has-text-centered">
        <p>
          <a href="/"> Impressum</a>
        </p>
        <p>
          <strong>
            Made with{' '}
            <span role="img" aria-label="Blue Heart">
              &#128153;
            </span>{' '}
            at htwsaar
          </strong>
        </p>
      </Content>
    </BulmaFooter>
  );
}

export default Footer;
