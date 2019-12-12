package org.sj.msuserrepo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.UUID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import junit.framework.TestCase;



@RunWith(MockitoJUnitRunner.class)
public class userControllerTest extends TestCase{
	
	
	@InjectMocks
	Controller userController;
	
	@Mock
	UserStoreRepo userStoreRepo;
	
	public userControllerTest()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddUser()
	{
		UserRepo user = new UserRepo();
		
		LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setPoaFileID("12341234");
        user.setPoiFileID("12341234");
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        
        //Mock repository methods
        when(userStoreRepo.save(any(UserRepo.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        //Testing function
        UserRepo response = userController.addUser(user);
        
        assertThat(response.equals(user));
	}
	
	@Test
	public void testGetUserWhenFound()
	{
		UserRepo user = new UserRepo();
		UserRepo user2 = new UserRepo();
		
		LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setPoaFileID("12341234");
        user.setPoiFileID("12341234");
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
		
        user2.setId(UUID.randomUUID());
        user2.setName("John");
        user2.setPoaFileID("12341234");
        user2.setPoiFileID("12341234");
        user2.setCreatedAt(date);
        user2.setUpdatedAt(date);
		
		//Mock repository methods
		when(userStoreRepo.findById(any(UUID.class))).thenAnswer(
				 new Answer<Optional<UserRepo>>() {
			         public Optional<UserRepo> answer(InvocationOnMock invocation) {
			             Object[] args =  invocation.getArguments();
			             return args[0].equals(user.getId())?Optional.ofNullable(user):Optional.ofNullable(user2);
			         }
			 });
		
		//Call function being tested
		UserRepo response = userController.getUser(user.getId());
		UserRepo response2 = userController.getUser(user2.getId());
		
		//Assertions
		assertEquals(response,user);
		assertEquals(response2,user2);
	}
	
	@Test
	public void testGetUserWhenNotFound()
	{
		//Mock repository methods
		when(userStoreRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(null));
		
		try {
			userController.getUser(UUID.randomUUID());
			//Exception not thrown
			fail("Resource not found exception not thrown");
		}
		catch(ResourceNotFoundException e)
		{
			//Exception caught as expected
		}
	}
	
	@Test
	public void testGetAllUsers()
	{
		UserRepo user = new UserRepo();
		UserRepo user2 = new UserRepo();
		
		LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setPoaFileID("12341234");
        user.setPoiFileID("12341234");
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
		
        user2.setId(UUID.randomUUID());
        user2.setName("John");
        user2.setPoaFileID("12341234");
        user2.setPoiFileID("12341234");
        user2.setCreatedAt(date);
        user2.setUpdatedAt(date);
        
        List<UserRepo> userList = new ArrayList<UserRepo>();
        userList.add(user);
        userList.add(user2);
        
        //Mocking repository methods
        when(userStoreRepo.findAll()).thenReturn(userList);
        
        //Testing function
        List<UserRepo> response = userController.getAllUsers();
        
        assertEquals(response,userList);
	}
	
	@Test
	public void testUpdateUser()
	{
		UserRepo user = new UserRepo();
		UserRepo user2 = new UserRepo();
		
		LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setPoaFileID("12341234");
        user.setPoiFileID("12341234");
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        
        user2.setPoaFileID("09876543");
        user2.setPoiFileID("09876543");
        
        //Mocking repository methods
        when(userStoreRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(userStoreRepo.save(any(UserRepo.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        //Testing function
        UserRepo response = userController.updateUser(user.getId(), user2);
        
        
        assertEquals(response.getPoaFileID(),user2.getPoaFileID());
        assertEquals(response.getPoiFileID(),user2.getPoiFileID());
	}
}
