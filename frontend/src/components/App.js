import React, { Component } from 'react';
import './App.css';

import { Header } from './Header';
import { MidiAnalysis } from './MidiAnalysis';
import { Footer } from './Footer'

class App extends Component {

  render() {
    return (
      <div className="App">
        <div className="Site-content">
          <div className="App-header">
              <Header />
          </div>
          <div>
            <MidiAnalysis/>
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
