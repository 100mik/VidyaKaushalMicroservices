import React, {Component} from 'react'; 
import Input from "../components/Input";
import Button from "../components/Button";
import Schema from "../schemas/Schemas.json";
import Ajv from 'ajv';
var ajv = Ajv({allErrors: true});

class FormContainer extends Component{
    constructor(props){
        super(props);
        this.state = {
            newUser: {
                name:'',
                email:'',
                phone:'',
                test:'' 
            },
            errors:[]
        }

        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.handleEmail = this.handleEmail.bind(this);
        this.handleTest = this.handleTest.bind(this);
        this.handleFullName = this.handleFullName.bind(this);
        this.handlePhone = this.handlePhone.bind(this);
    }

    handleFormSubmit(e){
        e.preventDefault();
        var data = this.state.newUser;
        
        this.setState( prevState => ({ newUser : 
            {...prevState.newUser}, errors: []}
        ));

        var valid = ajv.validate(Schema, data);
        if(!valid){
            ajv.errors.forEach(obj =>{
                switch(obj.dataPath){
                    case ".test" : this.state.errors.push("Test string should start with S and end with E or start with A and end with R"); break;
					case ".email" : this.state.errors.push("Invalid email format"); break;
					case ".phone" : this.state.errors.push("Phone numbers should contain only digits and be at least 10 digits long"); break;
					case ".name" : this.state.errors.push("Names can only have spaces and alphabets"); break;
					default:  this.state.errors.push("Invalid form data");
                }
            })
            
            this.setState( prevState => ({ newUser : 
                {...prevState.newUser}, errors: [...new Set(this.state.errors)]}
            ));
        }
    }


    handleFullName(e){
        let value = e.target.value;
        this.setState( prevState => ({ newUser : 
            {...prevState.newUser, name: value
            }
        }));
    }

    handleEmail(e){
        let value = e.target.value;
        this.setState( prevState => ({ newUser : 
            {...prevState.newUser, email: value
            }
        }));
        
    }

    handlePhone(e){
        let value = e.target.value;
        this.setState( prevState => ({ newUser : 
            {...prevState.newUser, phone: value
            }
        }));

    }

    handleTest(e){
        let value = e.target.value;
        this.setState( prevState => ({ newUser : 
            {...prevState.newUser, test: value
            }
        }));
    }
    

    render(){
        return(
            <form className="container" onSubmit={this.handleFormSubmit}>
                <Input type={'text'} 
                   name= {'name'}
                   value={this.state.newUser.name} 
                   placeholder = {'Enter your name'}
                   handleChange = {this.handleFullName}
                   />
                <Input type={'text'} 
                   name= {'email'}
                   value={this.state.newUser.email} 
                   placeholder = {'Enter email'}
                   handleChange = {this.handleEmail}
                   />
                <Input type={'text'}
                   name= {'phone'}
                   value={this.state.newUser.phone} 
                   placeholder = {'Enter phone'}
                   handleChange = {this.handlePhone}
                   />
                <Input type={'text'}
                   name= {'test'}
                   value={this.state.newUser.test} 
                   placeholder = {'Enter test string'}
                   handleChange = {this.handleTest}
                   />
                <Button title = {'Submit'}
                    onClick = {this.handleFormSubmit}
                />

                <div className = "HelpBlock">
                    {this.state.errors.map((error, i) => <p key={i}>{error}</p>)}
                </div>

                
            </form>
        )
    }   
}   

export default FormContainer;