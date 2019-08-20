import React, {Component} from 'react';
import {Bar, Line, Pie} from 'react-chartjs-2';
import './NoteDistributionBarChart.css';
/*
TODO make separate listcomponent for basic midi file analysis
(what midi file type, other basic data)
*/
class NoteDistributionBarChart extends Component {
  constructor(props){
    super(props);
    this.state = {
      noteList: [],
    }
  }
  
  static defaultProps = {
    displayTitle:true,
    displayLegent:true,
    legendPosition:'right'
  }



  
  render(){
    
    return(
      <div className="container">
      <div className="NoteDistributionPieChart" legendPosition="bottom">
      <Pie
          options={{
            title:{
              display:this.props.displayTitle,
              text:'Note Distributions',
              fontSize:25
            },
            legend:{
              display:this.props.displaylegend,
              position:this.props.legendposition
            }
          }}
          data={this.state.data}
      />
      </div>
      </div>
    )
  }
}
export default NoteDistributionBarChart;
