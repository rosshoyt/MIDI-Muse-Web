import React, { Component } from 'react'
import MidiFileList from './MidiFileList'
import { FileUpload } from './FileUpload';

export class LeftContainer extends Component {
    render() {
        return (
            <div>
                <FileUpload class = "fileUpload"/>
                <MidiFileList midiFileList={this.props.midiFileList} 
                                  idCurrentFileSelected={this.props.idCurrentFileSelected}
                                  fileListItemSelectedCallback={this.props.fileListItemSelectedCallback}
                />  
            </div>
        )
    }
}

export default LeftContainer
