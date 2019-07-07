import React, { Component } from 'react';
import Chart from './Chart'
import MidiFileList from './MidiFileList'
import "./MidiAnalysis.css"

const DEFAULT_FILE_SELECTION = "coolsong.mid";
const DEFAULT_FILE_ID = 1;
export class MidiAnalysis extends Component {
  constructor(props){
    super(props);
    this.state = {
      midiFileRepoContents: [],
      currentToggledFileID: DEFAULT_FILE_ID,
      isLoading: true
    }
  }


  render() {
    
        return (
          <div class="MidiAnalysis">
            <div>
              <MidiFileList/>
            </div>
            <div>
              <Chart/>
            </div>

        </div>
        );

    }
}
export default MidiAnalysis;
