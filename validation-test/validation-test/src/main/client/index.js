const Ajv = require('ajv');
var ajv = Ajv();
const fs = require('fs').promises;

var schema = {
		title:"schemas",
		anyOf : [{$ref:"#/definitions/user"}],
		definitions:{
		user:{
			type : "object",
			additionalProperties : false,
			properties:{
				name:{type:"string",pattern:"^[a-zA-Z ]*$"},
				email:{type:"string",pattern:"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"},
				phone:{type:"string",minLength:10, pattern:"^[0-9]*$"},
				test:{anyOf:[{$ref:"#/definitions/test1"},{$ref:"#/definitions/test2"}]}
			}
		},
		test1:{type:"string",pattern:"^S.+E$"},
		test2:{type:"string",pattern:"^A.+R$"}
		}
	};
 


module.exports = function(){
	
	
	var name =this.document.getElementById("nameField").value;
	var email = this.document.getElementById("emailField").value;
	var phone = this.document.getElementById("phoneField").value;
	var test = this.document.getElementById("testField").value;
	
	var req = {name:name, email:email, phone:phone, test:test};
	
	var valid = ajv.validate(schema,req);
	if(!valid)
	{
		alert(JSON.stringify(ajv.errors));
		return;
	}
	
	alert("valid form data");
	
	
}
