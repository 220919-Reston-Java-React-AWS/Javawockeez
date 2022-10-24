import React from 'react';
import logo from './logo.svg';
import './App.css';

//to scroll to the top of page when loaded
import {useEffect} from 'react';

/* this is what a componenet looks like


  so to reference this component '<App />'
*/


function App() {
  useEffect(() => {
    // scroll to top on page load
    window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
  }, []);
  
  /* the functinon returns something that looks like HTML but it is NOT HTML 
      
    it returns a JSX / TSX;
    Essentailly, its a new language that combines both HTML and JS together
      -Ex. want to put a HTML element inside a variable? you can
      you want to use a variable value inside an HTML? yep
  */
  
  let paragraph = <p>wow, I am storing an HTML element within a JS variable</p>

  let someValue = "Hello I am a String variable";



  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

        {paragraph}

        <p>
          Edit <code>src/App.tsx</code> and save to reload.
          {someValue}
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
