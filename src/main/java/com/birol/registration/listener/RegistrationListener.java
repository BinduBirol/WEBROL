package com.birol.registration.listener;


import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import com.birol.service.IUserService;
import com.birol.service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import com.birol.ems.service.EmailService;
import com.birol.persistence.model.User;
import com.birol.registration.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;
    
    @Autowired
    private UserService userService;
    
	@Autowired
    private EmailService emailService;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        try {
			this.confirmRegistration(event);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) throws UnsupportedEncodingException {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);
        try {
			emailService.sendRegMailMessage(event, user, token);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //final SimpleMailMessage email = constructEmailMessage(event, user, token);
        //mailSender.send(email);
    }

    //
    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) throws UnsupportedEncodingException {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message= "Dear "+user.getFirstName()+" "+user.getLastName()+",\n";
        message+="You registered successfully!\n\n";
        message+= "Your SECRET for Google Authenticator: "+user.getSecret()+"\n";
        String qrimage=userService.generateQRUrl(user);
        message+= "You will find the QR code to this link:\n"+qrimage+"\n\n";
        message+= messages.getMessage("message.regSuccLink", null, "To confirm your registration, please click on the below link.", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();       
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
