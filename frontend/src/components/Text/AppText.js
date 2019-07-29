import React, { Component } from 'react';


class AppText extends Component {
    render() {
      return (
        <AppText>
          <View>
            <div className="app-text-header">{this.props.content}</div>
            <div className="app-text-body">{this.props.children}</div>
           </View>
        </AppText>
      );
    }
}
export default AppText;