import React, { Component } from 'react';
import Chart from './Chart'
import MidiFileList from './MidiFileList'
import "./MidiAnalyzerContainer.css"
import { cpus } from 'os';
import { FileUpload } from './FileUpload';

const DEFAULT_FILE_SELECTION = "coolsong.mid";
const DEFAULT_FILE_ID = 1;
export class MidiAnalyzerContainer extends Component {
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
          <div class="MidiAnalyzerContainer">
                  <div className="rowC">
                    <div className="colC">
                      <FileUpload/>
                      <MidiFileList/> 
                      
                    </div>
                    
                    <Chart/>
                  </div>
                 
        </div>
        );

    }
}
export default MidiAnalyzerContainer;
