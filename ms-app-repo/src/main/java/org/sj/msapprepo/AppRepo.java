package org.sj.msapprepo;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Entity

@IdClass(AppRepoIdentity.class)
public class AppRepo {

//@EmbeddedId
//private AppRepoIdentity appRepoIdentity;
@Id
	private UUID 	userID;
public UUID getUserID() {
	return userID;
}

public void setUserID(UUID userID) {
	this.userID = userID;
}

public int getSchemeID() {
	return schemeID;
}

public void setSchemeID(int schemeID) {
	this.schemeID = schemeID;
}

public String getPoaFileID() {
	return poaFileID;
}

public void setPoaFileID(String poaFileID) {
	this.poaFileID = poaFileID;
}

public String getPoiFileID() {
	return poiFileID;
}

public void setPoiFileID(String poiFileID) {
	this.poiFileID = poiFileID;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

@Id
	private int 	schemeID;


	private String 	poaFileID;
	private String 	poiFileID;

	@JsonIgnore
	@Column(updatable = false)
@Temporal(TemporalType.TIMESTAMP)
@CreatedDate
private Date createdAt;

	@JsonIgnore
@Temporal(TemporalType.TIMESTAMP)
@LastModifiedDate
private Date updatedAt;
}
