import React, { Component } from 'react';
import "./MidiAnalysis.css"
export class MidiAnalysis extends Component {
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
          <div class="MidiAnalysis">
            <div class="col-4">
              <h5>Midi File Analysis List</h5>
              <div class="list-group" id="list-tab" role="tablist">
                {groups.map(group =>
                  <a class = "list-group-item list-group-item-action" id={group.id} data-toggle="list" href={group.fileName} role="tab" aria-controls="home">{group.fileName}</a>
                )}
              </div>
            </div>
            <div class="col-8">
              <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">...</div>
                <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">...</div>
                <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">...</div>
                <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">...</div>
              </div>
            </div>

          <div class="col-8">
            <div class="tab-content" id="nav-tabContent">
              <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">...</div>
              <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">...</div>
              <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">...</div>
              <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">...</div>
            </div>
          </div>
        </div>
        );

    }
}
