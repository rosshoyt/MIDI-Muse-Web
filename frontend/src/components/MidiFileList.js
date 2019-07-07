import React, { Component } from 'react';
import './MidiFileList.css'
export class MidiFileList extends Component {
  constructor(props){
    super(props);
    this.state = {
      midiFileRepoContents: [],
      isLoading: true,
      listItemSelected: -1
    }
  }
  
  listItemSelected(id = 1) {
      console.log("list item id# " + id + "selected");
  }
  updateChart(){

  }


  async componentDidMount() {
    const response = await fetch('/api/midifiles');
    const body = await response.json();
    this.setState({ midiFileRepoContents: body, isLoading: false });
  }

  render() {
    const { midiFileRepoContents, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
        return (
          <div class="panel panel-default">
          <div class="panel-heading"><h4 class="panel-title">Files:</h4>
          </div>
          <div class="rowC">
          <div class="panel-body">
              <div class="list-group" id="list-tab" role="tablist">
              {midiFileRepoContents.map(midifile =>
                <a class = "list-group-item list-group-item-action" 
                id={midifile.id}data-toggle="list"role="tab" 
                aria-controls="home"onClick={this.listItemSelected(midifile.id)}>
                {midifile.fileName}
                </a>
              )}
            </div>
          </div>
          </div>
          </div>
        );
    }
}
export default MidiFileList;
