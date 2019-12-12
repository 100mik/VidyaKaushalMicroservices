package org.sj.msfilemgmnt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.mockito.Answers;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import junit.framework.TestCase;
import org.sj.msfilemgmnt.model.FileStore;
import org.springframework.http.HttpStatus;
import org.sj.msfilemgmnt.FileStoreController;
import org.sj.msfilemgmnt.FileStoreRepo;
import org.sj.msfilemgmnt.ResourceNotFoundException;


@RunWith(MockitoJUnitRunner.class)
public class FileStoreControllerTest extends TestCase{
	
	
	@InjectMocks
	FileStoreController fileStoreController;
	
	@Mock
	FileStoreRepo fileStoreRepo;
	
	
	
	public FileStoreControllerTest()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStoreFile()
	{
		//Set up mock data
		LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
		FileStore file = new FileStore();
		file.setId(UUID.randomUUID());
		file.setRemoved(false);
		file.setCreatedAt(date);
		String myFile = "My File Content";
		byte[] fileContent = DatatypeConverter.parseBase64Binary(myFile);
		file.setFileContent(fileContent);
		file.setFileType(".txt");
		
		//Mock repository methods
		when(fileStoreRepo.save(any(FileStore.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
		
		//Call test function
		UUID returnId = fileStoreController.storeFile(file);
		
		//Assertions
		assertThat(returnId.equals(file.getId()));
	}
	
	@Test
	public void testGetFileWhenFound()
	{
		//Set up mock data
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
		FileStore file = new FileStore();
		file.setId(UUID.randomUUID());
		file.setRemoved(false);
		file.setCreatedAt(date);
		String myFile = "My File Content";
		byte[] fileContent = DatatypeConverter.parseBase64Binary(myFile);
		file.setFileContent(fileContent);
		file.setFileType(".txt");
		
		FileStore file2 = new FileStore();
		file2.setId(UUID.randomUUID());
		file2.setRemoved(false);
		file2.setCreatedAt(date);
		String myFile2 = "My File Content2";
		byte[] fileContent2 = DatatypeConverter.parseBase64Binary(myFile2);
		file2.setFileContent(fileContent2);
		file2.setFileType(".txt");
		
		//Mock repository methods
		when(fileStoreRepo.findById(any(UUID.class))).thenAnswer(
				 new Answer<Optional<FileStore>>() {
			         public Optional<FileStore> answer(InvocationOnMock invocation) {
			             Object[] args =  invocation.getArguments();
			             return args[0].equals(file.getId())?Optional.ofNullable(file):Optional.ofNullable(file2);
			         }
			 });
		
		//Call function being tested
		FileStore response = fileStoreController.getFile(file.getId());
		FileStore response2 = fileStoreController.getFile(file.getId());
		
		//Assertions
		assertThat(response.equals(file));
		assertThat(response2.equals(file2));
	}
	
	@Test
	public void testGetFileWhenNotFound()
	{
		//Mock repository methods
		when(fileStoreRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(null));
		
		try {
			fileStoreController.getFile(UUID.randomUUID());
			//Exception not thrown
			fail("Resource not found exception not thrown");
		}
		catch(ResourceNotFoundException e)
		{
			//Exception caught as expected
		}
	}
	
	@Test
	public void testRemoveFileWhenFound()
	{
		//Set up mock data
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
		FileStore file = new FileStore();
		file.setId(UUID.randomUUID());
		file.setRemoved(false);
		file.setCreatedAt(date);
		String myFile = "My File Content";
		byte[] fileContent = DatatypeConverter.parseBase64Binary(myFile);
		file.setFileContent(fileContent);
		file.setFileType(".txt");
		
		FileStore updatedFile = new FileStore();
		updatedFile = file;
		updatedFile.setRemoved(true);
		
		//Mock repository method
		when(fileStoreRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(file));
		when(fileStoreRepo.save(file)).thenReturn(updatedFile);
		
		//Call function being tested
		HttpStatus response = fileStoreController.updateFile(file.getId());
		
		//Assertions
		assertThat(response.equals(HttpStatus.OK));
	}
	
	@Test
	public void testRemoveFileWhenNotFound()
	{
		//Mock repository methods
		when(fileStoreRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(null));
				
		try {
			fileStoreController.updateFile(UUID.randomUUID());
			//Exception not thrown
			fail("Resource not found exception not thrown");
		}
		catch(ResourceNotFoundException e)
		{
			//Exception caught as expected
		}
	}
}
