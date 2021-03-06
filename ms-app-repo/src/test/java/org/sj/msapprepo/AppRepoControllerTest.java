package org.sj.msapprepo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import junit.framework.TestCase;
import org.sj.msapprepo.Controller;
import org.sj.msapprepo.AppRepoRepository;
import org.sj.msapprepo.AppRepoIdentity;

@RunWith(MockitoJUnitRunner.class)
public class AppRepoControllerTest extends TestCase{
	
	
	@InjectMocks
	Controller appController;
	
	@Mock
	AppRepoRepository appRepoRepository;
	
	
	public AppRepoControllerTest()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAppWhenUnique()
	{
		//Setting up mock        
        AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        AppRepoIdentity appId = new AppRepoIdentity();
        appId.setUserID(app1.getUserID());
        appId.setSchemeID(app1.getSchemeID());
         
        when(appRepoRepository.existsById(any(AppRepoIdentity.class))).thenReturn(false);
        when(appRepoRepository.save(any(AppRepo.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        //Call test function
       AppRepo response = appController.addApp(app1);
       
       //Test
       assertEquals(response,app1);        
	}
	
	@Test
	public void testAddAppWhenNotUnique()
	{
		//Setting up mock        
        AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        AppRepoIdentity appId = new AppRepoIdentity();
        appId.setUserID(app1.getUserID());
        appId.setSchemeID(app1.getSchemeID());
         
        when(appRepoRepository.existsById(any(AppRepoIdentity.class))).thenReturn(true);
        when(appRepoRepository.save(any(AppRepo.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        //Call test function
        try {
       appController.addApp(app1);
       
       fail("Exception not thrown when AppRepoIdentity is not unique");
        }
        catch(ResponseStatusException e) {
        	//Exception thrown as expected
        }
	}
	
	@Test
	public void testGetAppWhenFound()
	{
		
        AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        AppRepoIdentity appId = new AppRepoIdentity();
        appId.setUserID(app1.getUserID());
        appId.setSchemeID(app1.getSchemeID());
        
        
        when(appRepoRepository.findById(any(AppRepoIdentity.class))).thenReturn(Optional.ofNullable(app1));
        
        AppRepo response = appController.getApp(app1.getUserID(), app1.getSchemeID());
        
        assertEquals(response,app1);
	}
	
	@Test
	public void testGetAppWhenNotFound()
	{
		
        AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        AppRepoIdentity appId = new AppRepoIdentity();
        appId.setUserID(app1.getUserID());
        appId.setSchemeID(app1.getSchemeID());
        
        
        when(appRepoRepository.findById(any(AppRepoIdentity.class))).thenReturn(Optional.ofNullable(null));
        
        try {
        appController.getApp(app1.getUserID(), app1.getSchemeID());
        
        //Exception not thrown
        fail("ResponseStatusException with HTTP status Bad Request not thrown");
        }
        catch(ResponseStatusException e)
        {
        	//Exception thrown as expected
        }
	}
	
	@Test
	public void testGetAllApp()
	{
		AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        
        AppRepo app2 = new AppRepo();
        app2.setUserID(UUID.randomUUID());
        app2.setUpdatedAt(date);
        app2.setSchemeID(1234);
        app2.setPoiFileID("TestPoiFileID");
        app2.setPoaFileID("TestPoaFileID");
        app2.setCreatedAt(date);
        
        List<AppRepo> appList = new ArrayList<AppRepo>();
        appList.add(app1);
        appList.add(app2);
        
        when(appRepoRepository.findAll()).thenReturn(appList);
        
        List<AppRepo> response = appController.getAllApp();
        
        assertEquals(response,appList);
	}
	
	@Test
	public void testGetAllAppWhenEmpty()
	{
		
		List<AppRepo> appList= new ArrayList<AppRepo>();
		when(appRepoRepository.findAll()).thenReturn(appList);		
		
		try {
			appController.getAllApp();
			//Exception not thrown
			fail("ResponseStatusException with HTTP status Bad Request not thrown");
		}
		catch(ResponseStatusException e)
		{
			//Exception thrown as expected
		}
	}
	
	@Test
	public void testUpdateApp()
	{
		AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        
        when(appRepoRepository.findById(any(AppRepoIdentity.class))).thenReturn(Optional.ofNullable(app1));
        when(appRepoRepository.save(any(AppRepo.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        
        //Updating record
        UUID newUUID = UUID.randomUUID();
        int newSchemeId = 4321;
        
        AppRepo response = appController.updateApp(newUUID, newSchemeId, app1);
        
        app1.setUserID(newUUID);
        app1.setSchemeID(newSchemeId);
        
        assertEquals(response, app1);
	}
	
	@Test
	public void testUpdateAppWhenNotFound()
	{
		AppRepo app1 = new AppRepo();
        app1.setUserID(UUID.randomUUID());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        app1.setUpdatedAt(date);
        app1.setSchemeID(1234);
        app1.setPoiFileID("TestPoiFileID");
        app1.setPoaFileID("TestPoaFileID");
        app1.setCreatedAt(date);
        
		when(appRepoRepository.findById(any(AppRepoIdentity.class))).thenReturn(Optional.ofNullable(null));
		 
		UUID newUUID = UUID.randomUUID();
	    int newSchemeId = 4321;
	     
	    try {
	    appController.updateApp(newUUID, newSchemeId, app1);
	    //Exception not thrown
	    fail("ResponseStatusException with HTTP status Bad Request not thrown");
	    }
	    catch(ResponseStatusException e)
	    {
	     //Exception thrown as expected
	    }
	}
}
