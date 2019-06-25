import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import Header from './Header';
import Home from './Home';

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
          <img src={logo} className="App-logo" alt="logo" />
          <div>
            <form method="POST" encType="multipart/form-data" action="/">
              <table>
                <tbody>
                  <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                  <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
                </tbody>
              </table>
            </form>
          </div>
          <div>
            <ul>
              <li each="file : ${files}">
                  <a href="${file}" text="${file}" />
              </li>
            </ul>
          </div>

          <div className="App-intro">
            <h2>Midi File Analysis List</h2>
            {groups.map(group =>
              <div key={group.id}>
                {group.fileName}
              </div>
            )}
          </div>
        </header>

      </div>
    );
  }
}

export default App;
