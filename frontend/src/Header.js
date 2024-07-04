// src/Header.js
import React from 'react';

const Header = () => {
  return (
    <header style={styles.header}>
      <h1>My Responsive App</h1>
      <nav>
        <a href="#home">Home</a>
        <a href="#about">About</a>
        <a href="#contact">Contact</a>
      </nav>
    </header>
  );
};

const styles = {
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '10px 20px',
    backgroundColor: '#282c34',
    color: 'white',
  },
  nav: {
    display: 'flex',
    gap: '15px',
  },
};

export default Header;
