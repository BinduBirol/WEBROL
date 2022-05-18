package com.birol.ems.dto;


import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;



@Entity
public class EMPLOYEE_BASIC {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empid;	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userid;	
	@Column(columnDefinition = "varchar(255)")
	@Nullable
	private String username;
	@Lob
	private  byte [] emp_image;
	private int roleid;
	@Column(unique=true)
	private String email;
	private String first_name;
	private String last_name;
	private String social_security_number;
	private String private_email;
	private String phone;
	private String phone_eve;
	private String designation;
	private String workspace;
	private String nearest_chief;
	private String address_full;
	private String employee_Type;
	private String contract_start;
	private String contract_end;
	private String bank_name;
	private String account_number;
	private String clearing_number;
	private String table_tax;
	private String full_name;
	private String added_by;
	private String sex;
	private String closest_relative_full;
	@Lob
	private byte [] doc_cv;
	@Lob
	private byte [] doc_certificate;
	@Lob
	private byte [] doc_id;
	@Lob
	private byte [] doc_others;
	private boolean status;
	
	@Transient
	private MultipartFile emp_image_m;
	@Transient
	private MultipartFile doc_m_cv;
	@Transient
	private MultipartFile doc_m_crt;
	@Transient
	private MultipartFile doc_m_id;
	@Transient
	private MultipartFile doc_m_others;
	@Transient
	private String emp_image_encoded;
	@Transient
	private String emp_doc_encoded;
	@Transient
	private String emp_others_encoded;
	@Transient
	private String contact_remaining_period;
	@Transient
	private String contact_started_period;
	@Transient
	private String contact_status_str;
	@Transient
	private String contract_start_persent;
	@Transient
	private String contract_end_persent;	
	
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

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAdded_by() {
		return added_by;
	}
	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmp_image_encoded() {
		return emp_image_encoded;
	}
	public void setEmp_image_encoded(String emp_image_encoded) {
		this.emp_image_encoded = emp_image_encoded;
	}
	public String getEmp_doc_encoded() {
		return emp_doc_encoded;
	}
	public void setEmp_doc_encoded(String emp_doc_encoded) {
		this.emp_doc_encoded = emp_doc_encoded;
	}
	public MultipartFile getEmp_image_m() {
		return emp_image_m;
	}
	public void setEmp_image_m(MultipartFile emp_image_m) {
		this.emp_image_m = emp_image_m;
	}	
	
	public MultipartFile getDoc_m_cv() {
		return doc_m_cv;
	}
	public void setDoc_m_cv(MultipartFile doc_m_cv) {
		this.doc_m_cv = doc_m_cv;
	}
	public MultipartFile getDoc_m_crt() {
		return doc_m_crt;
	}
	public void setDoc_m_crt(MultipartFile doc_m_crt) {
		this.doc_m_crt = doc_m_crt;
	}
	public MultipartFile getDoc_m_id() {
		return doc_m_id;
	}
	public void setDoc_m_id(MultipartFile doc_m_id) {
		this.doc_m_id = doc_m_id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}	
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSocial_security_number() {
		return social_security_number;
	}
	public void setSocial_security_number(String social_security_number) {
		this.social_security_number = social_security_number;
	}
	public String getPrivate_email() {
		return private_email;
	}
	public void setPrivate_email(String private_email) {
		this.private_email = private_email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone_eve() {
		return phone_eve;
	}
	public void setPhone_eve(String phone_eve) {
		this.phone_eve = phone_eve;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public String getNearest_chief() {
		return nearest_chief;
	}
	public void setNearest_chief(String nearest_chief) {
		this.nearest_chief = nearest_chief;
	}
	public String getAddress_full() {
		return address_full;
	}
	public void setAddress_full(String address_full) {
		this.address_full = address_full;
	}
	public String getEmployee_Type() {
		return employee_Type;
	}
	public void setEmployee_Type(String employee_Type) {
		this.employee_Type = employee_Type;
	}
	public String getContract_start() {
		return contract_start;
	}
	public void setContract_start(String contract_start) {
		this.contract_start = contract_start;
	}
	public String getContract_end() {
		return contract_end;
	}
	public void setContract_end(String contract_end) {
		this.contract_end = contract_end;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getClearing_number() {
		return clearing_number;
	}
	public void setClearing_number(String clearing_number) {
		this.clearing_number = clearing_number;
	}
	public String getTable_tax() {
		return table_tax;
	}
	public void setTable_tax(String table_tax) {
		this.table_tax = table_tax;
	}
	public String getClosest_relative_full() {
		return closest_relative_full;
	}
	public void setClosest_relative_full(String closest_relative_full) {
		this.closest_relative_full = closest_relative_full;
	}	
	
	public byte [] getEmp_image() {
		return emp_image;
	}
	public void setEmp_image(byte [] emp_image) {
		this.emp_image = emp_image;
	}	
	
	public String getContact_remaining_period() {
		return contact_remaining_period;
	}
	public void setContact_remaining_period(String contact_remaining_period) {
		this.contact_remaining_period = contact_remaining_period;
	}
	public String getContact_status_str() {
		return contact_status_str;
	}
	public void setContact_status_str(String contact_status_str) {
		this.contact_status_str = contact_status_str;
	}	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public byte[] getDoc_cv() {
		return doc_cv;
	}
	public void setDoc_cv(byte[] doc_cv) {
		this.doc_cv = doc_cv;
	}
	public byte[] getDoc_certificate() {
		return doc_certificate;
	}
	public void setDoc_certificate(byte[] doc_certificate) {
		this.doc_certificate = doc_certificate;
	}
	public byte[] getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(byte[] doc_id) {
		this.doc_id = doc_id;
	}
	public String getContact_started_period() {
		return contact_started_period;
	}
	public void setContact_started_period(String contact_started_period) {
		this.contact_started_period = contact_started_period;
	}
	public String getContract_start_persent() {
		return contract_start_persent;
	}
	public void setContract_start_persent(String contract_start_persent) {
		this.contract_start_persent = contract_start_persent;
	}
	public String getContract_end_persent() {
		return contract_end_persent;
	}
	public void setContract_end_persent(String contract_end_persent) {
		this.contract_end_persent = contract_end_persent;
	}
	public byte[] getDoc_others() {
		return doc_others;
	}
	public void setDoc_others(byte[] doc_others) {
		this.doc_others = doc_others;
	}
	public MultipartFile getDoc_m_others() {
		return doc_m_others;
	}
	public void setDoc_m_others(MultipartFile doc_m_others) {
		this.doc_m_others = doc_m_others;
	}
	public String getEmp_others_encoded() {
		return emp_others_encoded;
	}
	public void setEmp_others_encoded(String emp_others_encoded) {
		this.emp_others_encoded = emp_others_encoded;
	}	

}
