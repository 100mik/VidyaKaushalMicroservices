package org.sj.msfilemgmnt;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.sj.msfilemgmnt.model.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class FileStoreController {
	
		@Autowired
		FileStoreRepo fileStoreRepoObj;

		/**
		 *
		 * To be used to store File { "fileContent:":"BASE64 FORMAT" }
		 *
		 */
		@PostMapping("/addFile")
		public UUID storeFile(@Valid @RequestBody FileStore fileObj) {
			fileObj.setFileSize(fileObj.getFileContent().length);
			System.out.println(System.currentTimeMillis()+"::Received a file to store with length:"+fileObj.getFileSize());
			log.debug("Received a file to store with length:"+fileObj.getFileSize());
			fileObj=fileStoreRepoObj.save(fileObj);
			UUID id=fileObj.getId();
			return (id);
		}

		/**
		 *
		 * To be used to get store File { "id:":"UUID FORMAT" }
		 *
		 */
		@GetMapping("/getFile/{id}")
		public FileStore getFile(@PathVariable (value = "id") UUID fileId) {
			return fileStoreRepoObj.findById(fileId).orElseThrow(() -> new ResourceNotFoundException("FileStore", "id", fileId));

		}


		/**
		 *
		 * To be used to remove store File { "id:":"UUID FORMAT" }
		 *
		 */
		@PatchMapping("/removeFile/{id}")
		public HttpStatus updateFile(@PathVariable (value = "id") UUID fileId) {
			FileStore oldFileObj=fileStoreRepoObj.findById(fileId).orElseThrow(() -> new ResourceNotFoundException("FileStore", "id", fileId));
			oldFileObj.setRemoved(true);
			return HttpStatus.OK;
		}

}
