import React, {Component} from 'react';
import FileContainer from "./containers/FormContainer"
import './App.css';


class App extends Component {
  render(){
    return(
      <div className = "Container">
        <h3>Registration Form</h3>
        <FileContainer />
      </div>
    )
  }
}

export default App;
