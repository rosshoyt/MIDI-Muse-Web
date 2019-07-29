import React, { Component } from 'react';
import FileUpload from './FileUpload';
import './MidiFileList.css'
export class MidiFileList extends Component {
  constructor(props){
    super(props);
    
  }

  listItemSelected = (id) => {
    console.log("list item "+ id + " clicked");
    this.props.fileListItemSelectedCallback(id);
  }
  updateChart(){
  
  }

  render() {
       return (
          
          <div class="panel panel-default">
          <div class="panel-heading"><h4 class="panel-title">Midi Files:</h4>
          </div>
          <div class="rowC">
          <div class="panel-body">
              <div class="list-group" id="list-tab" role="tablist">
              {this.props.midiFileList.map(midifile =>
                // <a class = "list-group-item list-group-item-action" 
                <button type="button" class="list-group-item list-group-item-action"
                id={midifile.id}data-toggle="list"role="tab" 
                aria-controls="home" 
                onClick={ () => this.listItemSelected(midifile.id) }>
                {midifile.fileName}
                </button>
              )}
            </div>
          </div>
          </div>
          </div>
        );
    }
}
export default MidiFileList;
