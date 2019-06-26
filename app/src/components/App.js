import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import { Header } from './Header';
import { Home } from './Home';

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  async componentDidMount() {
    const response = await fetch('/api/groups');
    const body = await response.json();
    this.setState({ groups: body, isLoading: false });
  }

  render() {
    const {groups, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <div className="row">
            <div className="col-xs-10 col-xs-offset-1">
              <Header/>
            </div>
          </div>
        </header>

          <div id="wrapper">
            <div id="left">Left side div</div>
            <div id="right">Right side div</div>
          </div>


          <div className="App-intro">
            <h2>Midi File Analysis List</h2>
            {groups.map(group =>
              <div key={group.id}>
                {group.fileName}
              </div>
            )}
          </div>

      </div>


    );
  }
}

export default App;
