import React from 'react';

const Input = (props) => {
  const inputStyle = {
    borderRadius: "8px",
    fontSize: "15px",
    padding: "5px",
    width: "70%"
  }
  
  return (  
  <div className="form-group">
    <label htmlFor={props.name} className="form-label">{props.title}</label>
    <input
      className="form-input"
      id={props.name}
      name={props.name}
      type={props.type}
      value={props.value}
      onChange={props.handleChange}
      placeholder={props.placeholder} 
      style = {inputStyle}
    />
  </div>
)
}

export default Input;