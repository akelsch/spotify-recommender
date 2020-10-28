import React from 'react';
import { Footer, Container, Content, Heading } from 'react-bulma-components';
import 'react-bulma-components/dist/react-bulma-components.min.css';
import styles from './FooterApp.module.css';

function FooterApp() {
  return (
    <div className={styles.footerApp}>
      <Footer className={styles.footer}>
        <Container>
          <Content style={{ textAlign: 'center' }} size="medium">
            <Container>
              <a href="/"> Impressum</a>
              <Heading size={5} className={styles.footerText}>
                Made with
                <span
                  role="img"
                  aria-label="emoji"
                  className={styles.footerEmoji}
                >
                  &#128153;
                </span>
                at htwsaar
              </Heading>
            </Container>
          </Content>
        </Container>
      </Footer>
    </div>
  );
}

export default FooterApp;
