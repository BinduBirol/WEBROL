package com.birol.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.birol.ems.dto.Mail;
import com.birol.persistence.model.User;
import com.birol.registration.OnRegistrationCompleteEvent;
import com.birol.service.IUserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
	private Environment env;
    @Autowired
    private MessageSource messagesSource;
    @Autowired
	IUserService userService;

    public void sendRegMailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String text= "Dear "+user.getFirstName()+" "+user.getLastName()+",<br/>";
        text+= messagesSource.getMessage("message.regSuccLink", null, "To confirm your registration, please click on the below link.", event.getLocale());
        text+="<br/>"+confirmationUrl;
        text+= "<br/>Your SECRET for Google Authenticator: "+user.getSecret();
        //text+= "\nYour QR code for Google Authenticator is Attached.";
        text+= "<br/>Scan this QR code using Google Authenticator app on your Android or iPhone device";
        
        //helper.addAttachment("favicon.ico", new ClassPathResource("favicon.ico"));        
        //String qrlink="https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth%3A%2F%2Ftotp%2FNETLIT_EMS_Application%3Abirolkarmaker%40gmail.com%3Fsecret%3D3HPBJIBV6UPCIIYZ%26issuer%3DNETLIT_EMS_Application";
        String qrimage=userService.generateQRUrl(user);
        String inlineImage = "<div style=\"text-align: left\"><img  src=\""+qrimage+"\"></img></div>";
        String qrcode_link= "You can also find the QR code to this link: <a href='"+qrimage+"'>QR Image</a>";

        helper.setText(text+inlineImage+qrcode_link, true);
        helper.setSubject(subject);
        helper.setTo(recipientAddress);
        helper.setFrom(env.getProperty("support.email"));

        emailSender.send(message);
    }
    
    public void sendSimpleMessage(Mail mail) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
        String inlineImage = "<img src=\"cid:logo.png\"></img><br/>";

        helper.setText(inlineImage + mail.getContent(), true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }

}