import React, { Component } from 'react';
import Chart from './Chart'
import MidiFileList from './MidiFileList'
import "./MidiAnalysis.css"
import { cpus } from 'os';

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
            <h3>Midi File Analysis Module</h3>
                  <div className="rowC">
                    <MidiFileList/>
                    <Chart/>
                  </div>
            
        </div>
        );

    }
}
export default MidiAnalysis;
