import React, { Component } from 'react';

let selectedFile = "no file selected";

class SelectedFileBox extends Component {
    
    constructor(props){
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div>
                <h3>Current Midi File: {this.props.fileName + '.' + this.props.fileExtension}</h3>
                
            </div>
        );
    }
}
export default SelectedFileBox;




