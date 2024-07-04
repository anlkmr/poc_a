// src/Home.js
import React from 'react';

const Home = () => {
  return (
    <main style={styles.main}>
      <h2>Welcome to My Responsive App</h2>
      <p>This is a simple, responsive React homepage with a header and footer.</p>
      <section style={styles.content}>
        <div style={styles.card}>
          <h3>Feature 1</h3>
          <p>Description of feature 1.</p>
        </div>
        <div style={styles.card}>
          <h3>Feature 2</h3>
          <p>Description of feature 2.</p>
        </div>
        <div style={styles.card}>
          <h3>Feature 3</h3>
          <p>Description of feature 3.</p>
        </div>
      </section>
    </main>
  );
};

const styles = {
  main: {
    padding: '20px',
    textAlign: 'center',
  },
  content: {
    display: 'flex',
    justifyContent: 'space-around',
    flexWrap: 'wrap',
    marginTop: '20px',
  },
  card: {
    backgroundColor: '#f9f9f9',
    padding: '15px',
    margin: '10px',
    borderRadius: '5px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    flex: '1 1 calc(33% - 20px)',
    boxSizing: 'border-box',
  },
};

export default Home;
