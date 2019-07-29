import React, { Component } from 'react';

import MidiFileList from './MidiFileList'
import "./BasicContainer.css"
import { cpus } from 'os';
import { FileUpload } from './FileUpload';
import AnalysisContainer from './AnalysisContainer';
import Analysis_Modal_Pie from './MidiFileDataDisplay/Analysis_Modal_Pie'

import BasicFileInfo from './MidiFileDataDisplay/BasicFileInfo'

const DEFAULT_FILE_ID = 1;
class BasicContainer extends Component {



  render() {
        return (
          <div>
            <div>
              <Analysis_Modal_Pie/>
            </div>
            <div>
              <BasicFileInfo/>
            </div>
            
          </div>
        );

    }
}
export default BasicContainer;
