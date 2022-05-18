package com.birol.ems.dto;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentid;
	private String cmpid;
	private long empid;
	private String message;
	private String empname;
	@Lob
	private byte[] image;
	@Transient
	private MultipartFile image_m;
	@Transient
	private String image_encoded;
	private Date created;
	private Date updated;

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public String getCmpid() {
		return cmpid;
	}

	public void setCmpid(String cmpid) {
		this.cmpid = cmpid;
	}

	

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public MultipartFile getImage_m() {
		return image_m;
	}

	public void setImage_m(MultipartFile image_m) {
		this.image_m = image_m;
	}

	public String getImage_encoded() {
		return image_encoded;
	}

	public void setImage_encoded(String image_encoded) {
		this.image_encoded = image_encoded;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	

	public long getCommentid() {
		return commentid;
	}

	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Comments(long commentid, String cmpid, long empid, String message, String empname, byte[] image,
			MultipartFile image_m, String image_encoded, Date created, Date updated) {
		super();
		this.commentid = commentid;
		this.cmpid = cmpid;
		this.empid = empid;
		this.message = message;
		this.empname = empname;
		this.image = image;
		this.image_m = image_m;
		this.image_encoded = image_encoded;
		this.created = created;
		this.updated = updated;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

}
