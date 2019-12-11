package org.sj.webeurekaclient;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.java.Log;

@SpringBootApplication
@EnableFeignClients
@RestController
@Log
public class WebEurekaClientApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WebEurekaClientApplication.class, args);
	}

	
	@Autowired
	Repo repo;

	@GetMapping("/list-users/user/getAllUsers/")
	public String getAllUsers() {
		log.log(Level.INFO, "serving get all users");
		return repo.getAllUsers();
	}

	@PostMapping("/create-user/user/addUser")
	public String addUser(@RequestBody String user) {
		log.log(Level.INFO, "serving add user {0}",user);
		return repo.addUser(user);
	}
	
	@GetMapping(value= {"/profile-file-upload/user/getUser/{id}","scheme-file-upload/user/getUser/{id}"})
	public String getUser(@PathVariable (value="id") UUID id) {
		log.log(Level.INFO, "Getting user with ID: {0}",id);
		return repo.getUser(id);
	}
		
	@PatchMapping("/profile-file-upload/user/updateUser/{id}")
	public String updateUser(@PathVariable (value="id") UUID id, @RequestBody String receivedUser) {
		log.log(Level.INFO, "Updating user with ID: {0} with details {1}",new Object[] {id,receivedUser});
		return repo.updateUser(id,receivedUser);
	}
	
	
	@PostMapping("/profile-file-upload/file/addFile")
	public UUID storeFile(@RequestBody String fileObj) {
		log.log(Level.INFO, "Storing File: {0}",fileObj);
		return repo.storeFile(fileObj);
	}
	
	@GetMapping(value= {"/profile-file-upload/file/getFile/{id}","/list-users/file/getFile/{id}","/list-apps/file/getFile/{id}"})
	public String getFile(@PathVariable (value = "id") UUID fileId) {
		log.log(Level.INFO, "Getting File with ID: {0}",fileId);
		return repo.getFile(fileId);
	}
	
	@PatchMapping(value= {"/profile-file-upload/file/removeFile/{id}","/scheme-file-upload/file/removeFile/{id}"})
	public HttpStatus updateFile(@PathVariable (value = "id") UUID fileId) {
		log.log(Level.INFO, "Removing File with ID: {0}",fileId);
		return repo.updateFile(fileId);
	}
	
		
	@PostMapping("/scheme-file-upload/app/addApp")
	String addApp(@RequestBody String apprepo) {
		log.log(Level.INFO, "Adding Application : {0}",apprepo);
		return repo.addApp(apprepo);
	}
	
	
	@GetMapping("/scheme-file-upload/app/getApp/{userid}/{schemeid}")
	String getApp(@PathVariable(value="userid") UUID userid, @PathVariable(value="schemeid") int schemeid) {
		log.log(Level.INFO, "Getting Application : User ID: {0} and Scheme ID: {1}",new Object[] {userid,schemeid});
		return repo.getApp(userid, schemeid);
	}
		
		
	@GetMapping("/list-apps/app/getAllApp")
		String getAllApp(){
		log.log(Level.INFO, "Getting All Application");
		return repo.getAllApp();
	}
	
	
	
	@PatchMapping("/scheme-file-upload/app/updateApp/{userid}/{schemeid}")
	String updateApp(@PathVariable UUID userid, @PathVariable int schemeid, @RequestBody String apprepo) {
		log.log(Level.INFO, "Updating Application : User ID: {0} and Scheme ID: {1} with details {2}",new Object[] {userid,schemeid,apprepo});
		return repo.updateApp(userid, schemeid, apprepo); 
	}
}
	
	@FeignClient("ms-api-gateway")//This is application name of API Gateway
	interface Repo {
		
		String USER="/user"; //This is used in API Gateway to route it to respective Micro-Service. These are configured Routes
		String APP="/app";//This is used in API Gateway to route it to respective Micro-Service. These are configured Routes
		String FILE="/file";//This is used in API Gateway to route it to respective Micro-Service. These are configured Routes
		
		@GetMapping(USER+"/getAllUsers")
		String getAllUsers();
		
		@PostMapping(value =USER+"/addUser", consumes = "application/json")
		public String addUser(@RequestBody String user); 
		
		@GetMapping(USER+"/getUser/{id}")
		public String getUser(@PathVariable (value="id") UUID id); 

		@PatchMapping(value =USER+"/updateUser/{id}", consumes = "application/json")
		public String updateUser(@PathVariable (value="id") UUID id, @RequestBody String receivedUser);
		
		@PostMapping(value=APP+"/addApp", consumes = "application/json")
		public String  addApp(@RequestBody String apprepo);
		
		@GetMapping(APP+"/getApp/{userid}/{schemeid}")
		public String getApp(@PathVariable (value="userid")  UUID userid, @PathVariable (value="schemeid") int schemeid);
		
		@GetMapping(APP+"/getAllApp")
		public String getAllApp();
		
		@PatchMapping(value=APP+"/updateApp/{userid}/{schemeid}", consumes = "application/json")
		public String updateApp(@PathVariable (value="userid") UUID userid, @PathVariable (value="schemeid") int schemeid, @RequestBody String apprepo);
		
		@PostMapping(value=FILE+"/addFile", consumes="application/json")
		public UUID storeFile(@RequestBody String fileObj);
		
		@GetMapping(FILE+"/getFile/{id}")
		public String getFile(@PathVariable (value = "id") UUID fileId);
		
		@PatchMapping(FILE+"/removeFile/{id}")
		public HttpStatus updateFile(@PathVariable (value = "id") UUID fileId);
	}

