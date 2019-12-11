package org.sj.msfilemgmnt.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class FileStore{
    

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private UUID id;
	
	@Column(nullable = false, updatable = false)
	private byte[] fileContent;
	
	@Column(nullable = false, updatable = false)
	private long fileSize;
	
	@Column(nullable = false, updatable = false)
	private String fileType;

	@Column(nullable = true, updatable = true)
	private boolean removed;


	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
}
	