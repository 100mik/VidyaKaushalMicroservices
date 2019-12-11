package org.sj.msfilemgmnt;

import java.util.UUID;
import org.sj.msfilemgmnt.model.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FileStoreRepo extends JpaRepository<FileStore, UUID>  {

}