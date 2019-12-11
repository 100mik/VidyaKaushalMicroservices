package org.sj.msapprepo;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.cfg.UniqueConstraintHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

	@Autowired
	AppRepoRepository appRepoRepository; 
	
	@PostMapping("/addApp")
	AppRepo addApp(@RequestBody AppRepo apprepo){
		
		AppRepo savedApprepo = null;
		
		AppRepoIdentity identity = new AppRepoIdentity();
		identity.setUserID(apprepo.getUserID());
		identity.setSchemeID(apprepo.getSchemeID());
		
		if(!appRepoRepository.existsById(identity)) {
			System.out.println("Record doesnt exists previously");
			savedApprepo=appRepoRepository.save(apprepo);
			System.out.println("Saved as new Record");
		}else {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Record was present, hence not saving", null);
		}
		return savedApprepo;
	}
	
	
	@GetMapping("/getApp/{userid}/{schemeid}")
	AppRepo getApp(@PathVariable UUID userid, @PathVariable int schemeid){
			
			AppRepoIdentity identity = new AppRepoIdentity();
			identity.setUserID(userid);
			identity.setSchemeID(schemeid);
			return appRepoRepository.findById(identity).orElseThrow(()->new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Record was present, hence not saving", null));
			
	}
	
	@GetMapping("/getAllApp")
	List<AppRepo> getAllApp(){
		List<AppRepo> theList=appRepoRepository.findAll();
		if(theList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Zero Record present, hence no list", null);
		}
		return theList;
	}
	
	@PatchMapping("/updateApp/{userid}/{schemeid}")
	AppRepo updateApp(@PathVariable UUID userid, @PathVariable int schemeid, @RequestBody AppRepo apprepo){
			AppRepo searchedApprepo = null;
			
			AppRepoIdentity identity = new AppRepoIdentity();
			identity.setUserID(userid);
			identity.setSchemeID(schemeid);
			
			appRepoRepository.findById(identity).orElseThrow(()->new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Record not found, hence not saving", null));
			apprepo.setUserID(userid);
			apprepo.setSchemeID(schemeid);
			return appRepoRepository.save(apprepo);
	}
	
}
