import React, { Component } from 'react';
import './FileUploadCollapsible.css'


class FileUploadCollapsible extends React.Component {
    constructor() {
      super();
      
      // Initial state
      this.state = { open: false };
      this.state = {
        file: null
      }
      this.onFormSubmit = this.onFormSubmit.bind(this)
      this.onChange = this.onChange.bind(this)
      this.fileUpload = this.fileUpload.bind(this)
    }
    onFormSubmit(e) {
      e.preventDefault() // Stop form submit
      this.fileUpload(this.state.file).then((response) => {
        console.log(response.data);
      })
    }
    onChange(e) {
      this.setState({ file: e.target.files[0] })
    }
    fileUpload(file) {
      const url = '/uploadmidifile';
      const formData = new FormData();
      formData.append('file', file)
      const config = {
        headers: {
          'content-type': 'multipart/form-data'
        }
      }
      return post(url, formData, config)
    }
    
    toggle() {
      this.setState({
        open: !this.state.open
      });
    }
  
    render() {
      return (
        <div className="cart">
         <button className="btn btn-block" onClick={this.toggle.bind(this)}>
                              Open/close
         </button>
          <div id="demo" className={"collapse" + (this.state.open ? ' in' : '')}>
            <div>
              
                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid.
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
            
            </div>
          </div>
        </div>
      );
    }
  
  };
  
  export default FileUploadCollapsible;