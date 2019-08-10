import React, { Component } from 'react';





export class BasicFileInfo extends Component {

  constructor(props){
    super(props);
  }

  render() {
        return (
         
          <div class="BasicFileInfo">
            <h4>
              General Midi File Information for {this.props.currentAnalysis.midiFileDetail.fullFileName}
            </h4>
            <h6>MIDI File Format: {this.props.currentAnalysis.midiFileDetail.format}</h6>
            <h6>Number of Tracks: {this.props.currentAnalysis.midiFileDetail.numTracks}</h6>  
            <h6>Rhythmic Division: {this.props.currentAnalysis.midiFileDetail.division}</h6>  
          </div>
        );

    }
}
export default BasicFileInfo;