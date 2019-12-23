import React from 'react';

const Button = (props) => {

    const buttonStyle = {backgroundColor: "#A8B9BD",
    textAlign : "center",
    borderRadius: "10px",
    border: "0",
    fontSize:"15px",
    padding: "8px",
    width: "40%"
    }

    return(
        <button 
            style= {buttonStyle} 
            onClick= {props.action}>    
            {props.title} 
        </button>)
}

export default Button;