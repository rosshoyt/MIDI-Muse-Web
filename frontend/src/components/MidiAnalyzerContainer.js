import React, { Component } from 'react';

import MidiFileList from './MidiFileList'
import "./MidiAnalyzerContainer.css"
import { cpus } from 'os';
import { FileUpload } from './FileUpload';
import AnalysisContainer from './AnalysisContainer';

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
                    
                    <AnalysisContainer/>
                  </div>
                 
        </div>
        );

    }
}
export default MidiAnalyzerContainer;
