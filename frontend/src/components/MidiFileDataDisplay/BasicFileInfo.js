import React, { Component } from 'react';





export class BasicFileInfo extends Component {

  constructor(props){
    super(props);
  }

  render() {
        return (
         
          <div class="BasicFileInfo">
            <h4>
              General Midi File Information
            </h4>
            <h6>MIDI File Format: {this.props.currentAnalysis.rawAnalysis.midiFileFormatType}</h6>
            <h6>Number of Tracks: {this.props.currentAnalysis.rawAnalysis.numTracks}</h6>  
            <h6>Number of Midi Messages: {this.props.currentAnalysis.rawAnalysis.numMidiMessages}</h6>  
          </div>
        );

    }
}
export default BasicFileInfo;