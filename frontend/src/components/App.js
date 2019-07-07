import React, { Component } from 'react';
import './App.css';

import { Header } from './Header';
import { MidiAnalysis } from './MidiAnalysis';
import { Footer } from './Footer'

class App extends Component {

  render() {
    return (
      <div className="App">
        <Header/>
          <div>
            <MidiAnalysis/>
          </div>
          <div>
            <Footer/>
          </div>
      </div>




    );
  }
}

export default App;
