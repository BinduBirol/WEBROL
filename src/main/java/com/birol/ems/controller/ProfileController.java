package com.birol.ems.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.birol.ems.dao.ComplaintsRepo;
import com.birol.ems.dto.EMPLOYEE_BASIC;
import com.birol.ems.repo.EmployeeRepository;
import com.birol.ems.service.EmployeeService;
import com.birol.persistence.dao.RoleRepository;
import com.birol.persistence.model.User;
import com.birol.security.ActiveUserStore;
import com.birol.service.IUserService;

@Controller
public class ProfileController {
	@Autowired
	ActiveUserStore activeUserStore;
	@Autowired
	com.birol.security.LoggedUser loggedUser;
	@Autowired
	IUserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ComplaintsRepo complaintsRepo;
	@Autowired
	com.birol.ems.dao.CommentRepo commentRepo;

	private static final Logger logger = LoggerFactory.getLogger(EMScontroller.class);

	@GetMapping("/profile")
	public ModelAndView profile(final ModelMap model, Authentication auth) {
		User user = (User) auth.getPrincipal();
		EMPLOYEE_BASIC empdtl = employeeRepository.findbyEmpid(user.getId());
		if (empdtl.getEmp_image() != null) {
			String imageencode = Base64.getEncoder().encodeToString(empdtl.getEmp_image());
			empdtl.setEmp_image_encoded(imageencode);
		}
		try {
			Period p = Period.between(LocalDate.now(), LocalDate.parse(empdtl.getContract_end().replace("/", "-")));
			String format_p = p.toString().replace("P", "").replace("Y", "Years ").replace("M", "Months ").replace("D",
					"Days");
			empdtl.setContact_status_str("align-middle");
			if (format_p.startsWith("-"))
				empdtl.setContact_status_str("text-danger");
			empdtl.setContact_remaining_period(format_p);
		} catch (Exception e2) {
			empdtl.setContact_remaining_period("Not spacified");
		}
		model.addAttribute("user", user);
		model.addAttribute("userdtl", empdtl);
		return new ModelAndView("ems/pages/profile", model);
	}

	@RequestMapping(value = "/savePersonalInfo", method = RequestMethod.POST)
	public ModelAndView savePersonalInfo(@ModelAttribute EMPLOYEE_BASIC personal, final ModelMap model) {
		EMPLOYEE_BASIC empbasic = new EMPLOYEE_BASIC();
		empbasic = employeeRepository.findbyEmpid(personal.getEmpid());
		try {
			if (personal.getEmp_image_m().getSize() > 0) {
				empbasic.setEmp_image(personal.getEmp_image_m().getBytes());
			}
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			logger.error(e.getMessage());
		}
		empbasic.setFirst_name(personal.getFirst_name());
		empbasic.setLast_name(personal.getLast_name());
		empbasic.setPrivate_email(personal.getPrivate_email());
		empbasic.setClosest_relative_full(personal.getClosest_relative_full());
		empbasic.setAddress_full(personal.getAddress_full());
		empbasic.setSocial_security_number(personal.getSocial_security_number());
		empbasic.setPhone_eve(personal.getPhone_eve());
		empbasic.setSex(personal.getSex());
		// set bank info
		empbasic.setBank_name(personal.getBank_name());
		empbasic.setAccount_number(personal.getAccount_number());
		empbasic.setClearing_number(personal.getClearing_number());
		empbasic.setTable_tax(personal.getTable_tax());
		// documents
		try {
			if (personal.getDoc_m_cv().getSize() > 0) {
				empbasic.setDoc_cv(personal.getDoc_m_cv().getBytes());
			}
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			logger.error(e.getMessage());
		}
		try {
			if (personal.getDoc_m_crt().getSize() > 0) {
				empbasic.setDoc_certificate(personal.getDoc_m_crt().getBytes());
			}
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			logger.error(e.getMessage());
		}
		try {
			if (personal.getDoc_m_id().getSize() > 0) {
				empbasic.setDoc_id(personal.getDoc_m_id().getBytes());
			}
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			logger.error(e.getMessage());
		}
		employeeRepository.save(empbasic);
		model.addAttribute("message", "Succesfully Updated Info For " + empbasic.getFirst_name());
		model.addAttribute("class", "alert alert-success");
		return new ModelAndView("theme/ajaxResponse", model);
	}
}
