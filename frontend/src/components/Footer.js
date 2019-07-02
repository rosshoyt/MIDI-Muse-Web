import React, { Component } from 'react';
//TODO Model code after this https://codesandbox.io/s/l7wryrr64l
import './Footer.css'
export class Footer extends Component {
    render() {
        return (
          <footer>
            <p>Website Alpha 1.0</p>
            <p>Analysis available under the <a href="https://www.apache.org/licenses/LICENSE-2.0">Apache Liscense 2.0</a></p>
            <p>© Ross Hoyt Music</p>
          </footer>
        );
    }
}
export default Footer;
