// src/Footer.js
import React from 'react';

const Footer = () => {
  return (
    <footer style={styles.footer}>
      <p>&copy; 2024 My Responsive App. All rights reserved.</p>
    </footer>
  );
};

const styles = {
  footer: {
    textAlign: 'center',
    padding: '10px 0',
    backgroundColor: '#282c34',
    color: 'white',
    position: 'absolute',
    bottom: '0',
    width: '100%',
  },
};

export default Footer;
