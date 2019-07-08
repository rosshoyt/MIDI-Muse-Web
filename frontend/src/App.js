import React, { Component } from 'react';
import './App.css';

import { Header } from './components/Header';
import { MidiAnalyzerContainer } from './components/MidiAnalyzerContainer';
import { Footer } from './components/Footer'

class App extends Component {

  render() {
    return (
      <div className="App">
        <div className="Site-content">
          <div className="App-header">
            <Header/>
          </div>
          <div>
            <MidiAnalyzerContainer/>
          </div>
          </div>
          <div>
          <Footer/>
          </div>
      </div>

//TODO FOOTER https://dev.to/letsbsocial1/flexbox-sticky-footer-and-react-e1h


    );
  }
}
export default App;
