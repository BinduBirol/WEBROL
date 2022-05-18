package com.birol.ems.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.birol.ems.dao.ComplaintsRepo;
import com.birol.ems.dto.Comments;
import com.birol.ems.dto.Complaints;
import com.birol.ems.dto.EMPLOYEE_BASIC;
import com.birol.ems.dto.GetChatMessage;
import com.birol.ems.dto.SendMessage;
import com.birol.ems.repo.EmployeeRepository;
import com.birol.ems.service.EmployeeService;
import com.birol.persistence.dao.RoleRepository;
import com.birol.persistence.model.User;
import com.birol.security.ActiveUserStore;
import com.birol.service.IUserService;

@Controller
public class InternalCommunicationController {
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

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public SendMessage greeting(GetChatMessage getmessage, Authentication auth) throws Exception {
		Thread.sleep(1000); // simulated delay
		User user = (User) auth.getPrincipal();
		EMPLOYEE_BASIC emp = employeeRepository.findbyEmpid(user.getId());
		SendMessage sendmsg = new SendMessage();
		sendmsg.setSender(emp.getFull_name());
		sendmsg.setContent(getmessage.getMessage());
		sendmsg.setTimeStamp(new SimpleDateFormat("hh.mm.ss a").format(new Date()));
		sendmsg.setSenderid(String.valueOf(user.getId()));
		sendmsg.setColor(getmessage.getColor());
		// return new Greeting(HtmlUtils.htmlEscape(message.getName()));
		return sendmsg;
	}

	@GetMapping("/deleteIssue")
	public ModelAndView deleteIssue(@RequestParam("cmpid") int cmpid, final ModelMap model) {
		Complaints cmp = complaintsRepo.findCmpById(cmpid);
		complaintsRepo.delete(cmp);
		for (Comments x : cmp.getComments()) {
			commentRepo.delete(x);
		}
		return new ModelAndView("redirect:/complaints", model);
	}

	@RequestMapping(value = "/doComment", method = RequestMethod.POST)
	public ModelAndView doComment(@ModelAttribute Comments comment, final ModelMap model, Authentication auth) {
		if (comment.getImage_m().getSize() > 0) {
			try {
				comment.setImage(comment.getImage_m().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		User user = (User) auth.getPrincipal();
		EMPLOYEE_BASIC emp = employeeRepository.findbyEmpid(user.getId());
		comment.setEmpid(emp.getEmpid());
		comment.setEmpname(emp.getFull_name());
		commentRepo.save(comment);
		return new ModelAndView("redirect:/complaints", model);
	}

	@GetMapping("/viewcomplaint")
	public ModelAndView viewcomplaint(@RequestParam("cmpid") int cmpid, final ModelMap model) {
		Complaints cmp = complaintsRepo.findCmpById(cmpid);
		// System.out.println(Arrays.toString(cmp.getComments().toArray()));
		if (cmp.getImage() != null) {
			String imageencode = Base64.getEncoder().encodeToString(cmp.getImage());
			cmp.setImage_encoded(imageencode);
		}
		for (Comments c : cmp.getComments()) {
			if (c.getImage() != null) {
				String imageencode = Base64.getEncoder().encodeToString(c.getImage());
				c.setImage_encoded(imageencode);
			}
		}
		model.addAttribute("cmp", cmp);
		return new ModelAndView("ems/ajaxResponse/viewComplaint", model);
	}

	@RequestMapping(value = "/doComplaints", method = RequestMethod.POST)
	public ModelAndView complaints(@ModelAttribute com.birol.ems.dto.Complaints cmp, ModelMap model,
			Authentication auth, final HttpServletRequest request) {
		try {
			User creator = (User) auth.getPrincipal();
			cmp.setEmpid(creator.getId());
			cmp.setEmpname(creator.getFirstName() + " " + creator.getLastName());
			if (cmp.getImage_m().getSize() > 0)
				cmp.setImage(cmp.getImage_m().getBytes());
			complaintsRepo.save(cmp);
			// System.out.println(cmp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/complaints", model);
	}

	@GetMapping("/complaints")
	public ModelAndView complaints(final ModelMap model) {
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("employees", employeeService.getEmployeeList());
		model.addAttribute("complaints", complaintsRepo.findAll());
		return new ModelAndView("ems/pages/complaints", model);
	}
}
