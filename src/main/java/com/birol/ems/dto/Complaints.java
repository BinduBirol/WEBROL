package com.birol.ems.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GeneratorType;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "complaints")
public class Complaints {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String description;
	private String empname;
	private long empid;
	@Column(name = "status", columnDefinition = "varchar(50) default 'REQUESTED'")
	private String status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cmpid")
	private Collection<Comments> comments;
	private String assign_to;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssign_to() {
		return assign_to;
	}

	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
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

	public Complaints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Comments> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Complaints [id=" + id + ", title=" + title + ", description=" + description + ", empname=" + empname
				+ ", empid=" + empid + ", status=" + status + ", comments=" + comments + ", assign_to=" + assign_to
				+ ", image=" + Arrays.toString(image) + ", image_m=" + image_m + ", image_encoded=" + image_encoded
				+ ", created=" + created + ", updated=" + updated + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getEmpname()=" + getEmpname() + ", getEmpid()=" + getEmpid() + ", getStatus()="
				+ getStatus() + ", getAssign_to()=" + getAssign_to() + ", getImage()=" + Arrays.toString(getImage())
				+ ", getImage_m()=" + getImage_m() + ", getImage_encoded()=" + getImage_encoded() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + ", getDescription()=" + getDescription()
				+ ", getComments()=" + getComments() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	

}
