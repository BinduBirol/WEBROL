package com.birol.ems.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.birol.ems.dto.EMPLOYEE_BASIC;




public interface EmployeeRepository extends CrudRepository<EMPLOYEE_BASIC, String> {
	
	String getbyroleQ= "SELECT * FROM employee_basic WHERE designation like ?1  ";
	@Query(value =getbyroleQ,nativeQuery = true)
	ArrayList<EMPLOYEE_BASIC> findbyrole( String roleid);
	
	String getbyidQ= "SELECT * FROM employee_basic WHERE userid = ?1  ";
	@Query(value =getbyidQ,nativeQuery = true)
	EMPLOYEE_BASIC findbyUserid(Long id);
	
	String getbyempidQ= "SELECT * FROM employee_basic WHERE empid = ?1  ";
	@Query(value =getbyempidQ,nativeQuery = true)
	EMPLOYEE_BASIC findbyEmpid(Long id);
	
	String getbyemailQ= "SELECT * FROM employee_basic WHERE email = ?1  ";
	@Query(value =getbyemailQ,nativeQuery = true)
	EMPLOYEE_BASIC findbyWorkMail(String mail);
	
	String getbyStatusQ= "SELECT * FROM employee_basic WHERE status = ?1  ";
	@Query(value =getbyStatusQ,nativeQuery = true)
	ArrayList<EMPLOYEE_BASIC> findbyStatus(int s);
}
