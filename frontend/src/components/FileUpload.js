import React, { Component } from 'react';

export class FileUpload extends Component {
    render() {
        return (
          <div>
            <div>
              <form method="POST" encType="multipart/form-data" action="/">
                <table>
                  <tbody>
                    <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                    <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
                  </tbody>
                </table>
              </form>
            </div>
            <div>
              <ul>
                <li each="file : ${files}">
                    <a href="${file}" text="${file}" />
                </li>
              </ul>
            </div>
          </div>
        );
    }
}
