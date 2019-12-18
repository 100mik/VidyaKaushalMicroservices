package com.validationtest.validationtest;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

@RestController
public class TestController {
	
	List<UserRepo> UserList = new ArrayList<UserRepo>();
	
	static ObjectMapper mapper = new ObjectMapper();
	static File file = new File("D:\\Soumik-WS\\validation-test\\validation-test\\src\\main\\resources\\Schemas.json");
	static JsonSchema validationSchema;
	
	static {
	try {
	JsonNode schemaNode = mapper.readTree(file);
	JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
	validationSchema = factory.getSchema(schemaNode);
	}catch(IOException e)
	{
		System.out.println("Error while creating schema JsonNode"+e.toString());
	}
	}
	
	@GetMapping("/hello")
	public Map<String,String> hello()
	{
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", "hello");
		return m;
	}
	
	@GetMapping("/getUsers")
	public List<UserRepo> getUsers(){
		
		return UserList;
	}
	
	@PostMapping("/addUser")
	public ValidationErrorMessage addUser(@RequestBody UserRepo newUser)
	{
		ValidationErrorMessage res = new ValidationErrorMessage();
		JsonNode requestBody = mapper.valueToTree(newUser);
		Set<ValidationMessage> errors =validationSchema.validate(requestBody); 
		if(!errors.isEmpty())
		{
			res.setStatus(false);
			res.setErrors(errors);
/*			for(ValidationMessage err : errors)
			{
				switch(err.getPath())
				{
					case "$.test" : res.errors.add("Test string should start with S and end with E or start with A and end with R"); break;
					case "$.email" : res.errors.add("Invalid email format"); break;
					case "$.phone" : res.errors.add("Phone numbers should contain only digits and be at least 10 digits long"); break;
					case "$.name" : res.errors.add("Names can only have spaces and alphabets"); break;
					default:  res.errors.add("Invalid form data");
				}
			}*/
			
			return res;
		}
		
		System.out.print(newUser);
		UserList.add(newUser);
		
		res.setErrors(null);
		res.setStatus(true);
		return res;
	}
}
