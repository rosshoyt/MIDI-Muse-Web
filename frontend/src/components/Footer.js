import React, { Component } from 'react';
//TODO Model code after this https://codesandbox.io/s/l7wryrr64l
import './Footer.css'
export class Footer extends Component {
    render() {
        return (
          <footer>
            <p>Alpha V1.1</p>
            <p>Musical Analysis available under the <a href="https://www.apache.org/licenses/LICENSE-2.0">Apache Liscense 2.0</a></p>
            <p><a href="https://www.rosshoyt.com">Ross Hoyt Music</a></p>
          </footer>
        );
    }
}
export default Footer;
