import React, { Component } from 'react';
import { MidiParser } from 'midi-parser-js';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';


function processFile(){
  
}  

export class FileUpload extends Component {

  render() {
      return (
        <Form>
        <FormGroup>
        <h3>Select a MIDI file to analyze.</h3>
        <h4>You can either:</h4>
        <h5>1. Upload a .mid or .midi file</h5>
        <h5>2. Or, select one of the pre-loaded examples</h5>
        <Input type="file" name="file" id="exampleFile" />
        <div>
          Note: Files uploaded are not stored
        </div>
        </FormGroup>
        <Button>Submit</Button>
        </Form>
        );
        
    }
}
export default FileUpload;