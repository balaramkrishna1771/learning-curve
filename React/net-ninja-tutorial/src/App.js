import Navbar from './components/Navbar.jsx';
import Home from './components/Home.jsx';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import BlogPreview from './components/BlogPreview.jsx';
import Create from './components/Create.jsx';
import ThemeProvider from './components/ThemeProvider.jsx';
import ThemedButton from './components/ThemedButton.jsx';
import { useState } from 'react';

function App() {
  const [count, setCount] = useState(0);

  const result = count * 28;


  return (
    <>
      <p>{count}<br />
        {result}</p>
      <button onClick={() => setCount(prev => (prev + 1))}>Increment</button>
      <ThemeProvider>
        <ThemedButton />
      </ThemeProvider>
    </>

  );
}

export default App;
