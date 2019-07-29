import React, { Component } from 'react'

export class MidiFileListItem extends Component {
    render() {
        return (
            <div>
                <p> { this.props.todo } </p>
            </div>
        )
    }
}

export default MidiFileListItem
