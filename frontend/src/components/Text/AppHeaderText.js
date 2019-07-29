import React, { Component } from 'react';

class AppHeaderText extends Component {
    render() {
      return (
        <AppText>
          <Text style={{fontSize: 20}}>{this.props.children}</Text>
        </AppText>
      );
    }
}
export default AppHeaderText;