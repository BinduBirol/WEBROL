package com.birol.ems.controller;

//import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import com.birol.ems.dao.ComplaintsRepo;
import com.birol.ems.dto.EMPLOYEE_BASIC;
import com.birol.ems.dto.Mail;
import com.birol.ems.repo.EmployeeRepository;
import com.birol.ems.service.EmailService;
import com.birol.ems.service.EmployeeService;
import com.birol.persistence.dao.RoleRepository;
import com.birol.persistence.model.User;
import com.birol.security.ActiveUserStore;
import com.birol.service.IUserService;

@Controller
public class EMScontroller {
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

	@GetMapping("/dashboard")
	public ModelAndView dashboard(final ModelMap model, Authentication auth) {
		List<String> getUsersFromActiveStore= activeUserStore.getUsers();
		model.addAttribute("loggedInUsers", getUsersFromActiveStore);
		List<String> getUsersFromSessionRegistry = userService.getUsersFromSessionRegistry();
		model.addAttribute("getUsersFromSessionRegistry", getUsersFromSessionRegistry);
		List<User> getAlluser = userService.findAllUser();
		model.addAttribute("getAlluser", getAlluser);
		User user = (User) auth.getPrincipal();
		return new ModelAndView("homepage", model);
	}

	@GetMapping("/calendar")
	public ModelAndView calander(final ModelMap model) {
		return new ModelAndView("ems/pages/calendar", model);
	}

	@GetMapping("/changePassword")
	public ModelAndView changePassword(final ModelMap model) {
		return new ModelAndView("ems/pages/changePassword", model);
	}

	@GetMapping("/settings")
	public ModelAndView settings(final ModelMap model) {
		return new ModelAndView("ems/pages/setting", model);
	}

	@GetMapping("/messaging")
	public ModelAndView messaging(final ModelMap model) {
		return new ModelAndView("ems/pages/messaging", model);
	}

	@GetMapping("/test")
	public ModelAndView test(final ModelMap model) throws MessagingException {

		// logger.info("Spring Mail - Sending Email with Inline Attachment Example");
		model.addAttribute("message", "Success");
		return new ModelAndView("theme/ajaxResponse", model);
	}

	@GetMapping("/download/user")
	public void downloadUsersFile(@RequestParam String fileId, @RequestParam Long userid, HttpServletResponse resp,
			Principal principal) throws IOException {

		EMPLOYEE_BASIC emp = new EMPLOYEE_BASIC();
		emp = employeeRepository.findbyEmpid(userid);
		byte[] byteArray = null;
		String ext = ".pdf";
		if (fileId.equalsIgnoreCase("cv")) {
			byteArray = emp.getDoc_cv();
			// ext=Magic.getMagicMatch(bdata).getExtension();
		} else if (fileId.equalsIgnoreCase("crt")) {
			byteArray = emp.getDoc_certificate();
		} else if (fileId.equalsIgnoreCase("others")) {
			byteArray = emp.getDoc_others();
		} else if (fileId.equalsIgnoreCase("id")) {
			byteArray = emp.getDoc_id();
			InputStream is = new ByteArrayInputStream(byteArray);
			String mimeType = URLConnection.guessContentTypeFromStream(is);
			if (mimeType != null) {
				ext = "." + mimeType.split("/")[1];
			}
			System.out.println(mimeType);
		}
		resp.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
		resp.setHeader("Content-Disposition", "attachment; filename=" + emp.getEmail() + fileId + ext);
		resp.setContentLength(byteArray.length);
		OutputStream os = resp.getOutputStream();
		try {
			os.write(byteArray, 0, byteArray.length);
		} finally {
			os.close();
		}
	}

}
