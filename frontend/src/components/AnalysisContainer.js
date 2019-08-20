import React, {Component} from 'react';
import { TabContent, TabPane, Nav, NavItem, NavLink, Card, Button, CardTitle, CardText, Row, Col } from 'reactstrap';
import classnames from 'classnames';
import Analysis_Modal_Pie from './MidiFileDataDisplay/Analysis_Modal_Pie'
import BasicFileInfo from './MidiFileDataDisplay/BasicFileInfo.js';
import NoteDistributionBarChart from './MidiFileDataDisplay/NoteDistributionBarChart'

export default class AnalysisContainer extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      activeTab: '1'
    };
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }
  render() {
    return (
      <div>
        {/* <div>
          <SelectedFileBox fileName={this.props.currentAnalysis.fileName}
                                   fileExtension={this.props.currentAnalysis.fileExtension}
                  />

        </div> */}
          <Nav tabs>
          <NavItem>
            <NavLink
              className={classnames({ active: this.state.activeTab === '1' })}
              onClick={() => { this.toggle('1'); }}
            >
            File Info
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: this.state.activeTab === '2' })}
              onClick={() => { this.toggle('2'); }}
            >
              Modal Analysis
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: this.state.activeTab === '3' })}
              onClick={() => { this.toggle('3'); }}
            >
              Misc stuff {'&'} thangz
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: this.state.activeTab === '5' })}
              onClick={() => { this.toggle('4'); }}
            >
              Note Distributions
            </NavLink>
          </NavItem>
        </Nav>
        <TabContent activeTab={this.state.activeTab}>
          <TabPane tabId="1">
            <BasicFileInfo
              currentAnalysis={this.props.currentAnalysis}
            />
          </TabPane>
          <TabPane tabId="2">
                <Analysis_Modal_Pie/>
          </TabPane>
          <TabPane tabId="3">
            <Row>
              <Col sm="6">
                <Card body>
                  <CardTitle>Special Title Treatment</CardTitle>
                  <CardText>With supporting text below as a natural lead-in to additional content.</CardText>
                  <Button>Go somewhere</Button>
                </Card>
              </Col>
              <Col sm="6">
                <Card body>
                  <CardTitle>Special Title Treatment</CardTitle>
                  <CardText>With supporting text below as a natural lead-in to additional content.</CardText>
                  <Button>Go somewhere</Button>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane tabId="4">

                <NoteDistributionBarChart noteList = {this.props.currentAnalysis.musicalAnalysis.even}/>

          </TabPane>
        </TabContent>
      </div>
    );
  }
}
