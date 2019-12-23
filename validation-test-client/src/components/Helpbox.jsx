import React from 'react';

const Helpbox = (props) => {
    const helpStyle = {
        backgroundColor: "red",
        color: "white",
        textAlign: "center",
        padding: "2px",
        fontSize : "14px",
        marginTop:"5%",
        marginBottom: "5%",
        borderRadius: "10px"
    }
    return (
        <div className = "Helpbox" style = {helpStyle}>
            {props.errors.map((error, i) => <p key={i}>{error}</p>)}
        </div>
    )
};



export default Helpbox;