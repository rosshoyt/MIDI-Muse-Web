import React, {Component} from 'react';
import {Bar, Line, Pie} from 'react-chartjs-2';
import './Chart.css';

/*
TODO make separate listcomponent for basic midi file analysis
(what midi file type, other basic data)
*/
class Chart extends Component {
  constructor(props){
    super(props);
    this.state = {
      midiFileRepoContents: [],
      chartData:props.chartData,
      data: {
        labels: [
          "C major", "G major", "D major", "A major",
          "E major","B major","F# major","C# major",
          "G# major","D# major","A# major","F major",  
        ],
        datasets: [
          {
            label: "Modes Used",
            data:
            [ 
              25, 0, 5, 0,
              0,  0, 0, 0,
              15, 0, 0, 0
            ]
          }
        ]
      }
    }
  }
  
  static defaultProps = {
    displayTitle:true,
    displayLegent:true,
    legendPosition:'right'
  }

  async componentDidMount() {
    const response = await fetch('/api/midifile/1');
    const body = await response.json();
    this.setState({ midiFileRepoContents: body, isLoading: false });
  }

  fetchSongData() {
    
  }
  
  render(){
    return(
      <div className="container">
      <div className="chart" legendPosition="bottom">
      <Pie
          options={{
            title:{
              display:this.props.displayTitle,
              text:'Modal Analysis',
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
export default Chart;
