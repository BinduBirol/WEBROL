package com.birol.ems.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.birol.ems.dto.EMPLOYEE_BASIC;
import com.birol.ems.repo.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public ArrayList<EMPLOYEE_BASIC> getEmployeeList() {
		ArrayList<EMPLOYEE_BASIC> eList_all = new ArrayList<EMPLOYEE_BASIC>();
		try {			
			eList_all= (ArrayList<EMPLOYEE_BASIC>) employeeRepository.findAll();
			for(EMPLOYEE_BASIC e: eList_all) {
				//setting up image
				if(e.getEmp_image()!=null) {
					String imageencode = Base64.getEncoder().encodeToString(e.getEmp_image());
			    	e.setEmp_image_encoded(imageencode);			    	
				}
				try {
					Period p = Period.between(LocalDate.now(),LocalDate.parse(e.getContract_end().replace("/", "-")));				
					String format_p=p.toString().replace("P", "").replace("Y", "Years ").replace("M", "Months ").replace("D", "Days");				
					e.setContact_status_str("align-middle");
					if(format_p.startsWith("-"))e.setContact_status_str("text-danger");
					e.setContact_remaining_period(format_p);
				} catch (Exception e2) {
					e.setContact_remaining_period("Permanent");
				}
				
				
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();			
		}
		return eList_all;
	}

}
