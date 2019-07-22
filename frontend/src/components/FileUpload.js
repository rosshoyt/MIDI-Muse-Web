import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import axios, { post } from 'axios';


function processFile(file){
  
}  

export class FileUpload extends Component {
  constructor(props) {
    super(props);
    this.state ={
      file:null
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
  }
  onFormSubmit(e){
    e.preventDefault() // Stop form submit
    this.fileUpload(this.state.file).then((response)=>{
      console.log(response.data);
    })
  }
  onChange(e) {
    this.setState({file:e.target.files[0]})
  }
  fileUpload(file){
    const url = '/uploadmidifile';
    const formData = new FormData();
    formData.append('file',file)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    return  post(url, formData,config)
  }
  render() {
      return (
        <Form onSubmit={this.onFormSubmit}>
          <FormGroup>
            <h3>Select a MIDI file to analyze.</h3>
            <h4>You can either:</h4>
            <p>1. Upload a .mid or .midi file</p>
            <p>2. Or, select one of the pre-loaded examples</p>
            <Input type="file" name="file" id="exampleFile" onChange={this.onChange}/>
        </FormGroup>
        <Button>Submit</Button>
        </Form>
        );
        
    }
}
export default FileUpload;