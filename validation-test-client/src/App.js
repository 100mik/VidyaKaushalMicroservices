import React, {Component} from 'react';
import FileContainer from "./containers/FormContainer"
import './App.css';


class App extends Component {
  render(){
    const headerStyle = {
      backgroundColor: "#1C2841",
      textAlign: "center",
      padding: "40px",
      verticalAlign: "middle"
    }
    
    const headTextStyle = {
      color:"#FFFFFF",
      verticalAlign: "middle",
      fontSize: 40
    }

    return(
      <div className = "Container"style = {headerStyle}>
        <h3  style = {headTextStyle}>Registration Form</h3>
        <FileContainer />
      </div>
    )
  }
}

export default App;
