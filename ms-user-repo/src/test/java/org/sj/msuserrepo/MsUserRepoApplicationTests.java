package org.sj.msuserrepo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
@SpringBootTest
public class MsUserRepoApplicationTests {

	@Autowired
    private MockMvc mvc;
 
    /*@MockBean
    private EmployeeService service;*/
	
	@Test
	public void contextLoads() {
	}
	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
	  throws Exception {
	     
	    UserRepo alex = new UserRepo();
	    alex.setName("alex"); 
	    alex.setPoaFileID("123123123");
	    alex.setPoiFileID("123123122");
	    
	 
	    /*List<Employee> allEmployees = Arrays.asList(alex);
	 
	    given(service.getAllEmployees()).willReturn(allEmployees);*/
	 /*
	    mvc.perform(post("/addUser")
	      .contentType(MediaType.APPLICATION_JSON))
	    
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].name", is(alex.getName())));*/
	}
}

