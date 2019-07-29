import React, { Component } from 'react';

import MidiFileList from './MidiFileList'
import "./MidiAnalyzerContainer.css"
import { cpus } from 'os';
import { FileUpload } from './FileUpload';
import AnalysisContainer from './AnalysisContainer';
import SelectedFileBox from './SelectedFileBox.js';


let DEFAULT_SELECTION_ID = 1;
// let listItemSelected;
export class MidiAnalyzerContainer extends Component {
  constructor(props){
    super(props);
    this.state = {
      isLoading: true,
      midiFileList: [],
      currentAnalysis: []
    }
    //listItemSelected = DEFAULT_SELECTION_ID;
  }
  
  async componentDidMount() {
    this.fetchMidiFileList();
    this.fetchMidiFileAnalysis();
  }
  async fetchMidiFileList() {
    const response = await fetch('/api/midifiles');
    const body = await response.json(); 
    this.setState({ midiFileList: body, isLoading: false });
  }
  async fetchMidiFileAnalysis(id = DEFAULT_SELECTION_ID){
    const response = await fetch("/api/midifileanalysis/" + id);
    const body = await response.json(); 
    this.setState({ currentAnalysis: body, isLoading: false });
  }

  fileListItemSelectedCallback = (id) => {
    this.fetchMidiFileAnalysis(id);
  }

  render() {
    const { isLoading, midiFileList, currentAnalysis } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
        return (
         
          <div class="MidiAnalyzerContainer">
                  <SelectedFileBox fileName={currentAnalysis.midiFile.fileName}
                                   fileExtension={currentAnalysis.midiFile.fileExtension}
                  />
                  <div className="rowC">
                    <div className="colC">
                    <FileUpload/>
                    <MidiFileList midiFileList={midiFileList} 
                                  idCurrentFileSelected={currentAnalysis.id}
                                  fileListItemSelectedCallback={this.fileListItemSelectedCallback}
                    />  
                      
                    </div>
                    
                    
                  </div>
                 
          </div>
        );

    }
}
export default MidiAnalyzerContainer;
