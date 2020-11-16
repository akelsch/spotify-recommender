import React from 'react';
import { Footer, Content, Heading } from 'react-bulma-components';
import styles from './FooterApp.module.css';

function FooterApp() {
  return (
    <div className={styles.footerApp}>
      <Footer className={styles.footer}>
        <Content style={{ textAlign: 'center' }} size="medium">
          <a href="/"> Impressum</a>
          <Heading size={5} className={styles.footerText}>
            Made with
            <span role="img" aria-label="emoji" className={styles.footerEmoji}>
              &#128153;
            </span>
            at htwsaar
          </Heading>
        </Content>
      </Footer>
    </div>
  );
}

export default FooterApp;
