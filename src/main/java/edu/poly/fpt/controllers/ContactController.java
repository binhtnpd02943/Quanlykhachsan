package edu.poly.fpt.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/")
	public String contact() {
		return "customer/contact";
	}
	@PostMapping("/sendMail")
	public String submitContact(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		String fullname  = request.getParameter("fullname");
		String email  = request.getParameter("email");
		String subject  = request.getParameter("subject");
		String content  = request.getParameter("content");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		
		
		String mailSubject = fullname + "has send a message";
		String mailContent = "<p><b>Sender Name: </b>" + fullname + "</p>"; 
	    mailContent += "<p><b>Sender E-mail: </b>" + email + "</p>"; 
	    mailContent += "<p><b>Subject: </b>" + subject + "</p>"; 
	    mailContent += "<p><b>Content: </b>" + content + "</p>";
	    mailContent += "<hr><img src= 'cid:logoImage' />";
	    
	    helper.setFrom("binhblue@gmail.com","Hotel Contact");
	    helper.setTo("tranbinh00k@gmail.com");
	    helper.setSubject(mailSubject);
	    helper.setText(mailContent, true);
	    
	    ClassPathResource resource = new ClassPathResource("/static/images/logo.png");
	    helper.addInline("logoImage", resource);
	    
	    mailSender.send(message);
				
	    return "customer/message";
	}
	@GetMapping("/about")
	public String about() {
		return "customer/about";
	}
	@GetMapping("/blog-list")
	public String bloglist() {
		return "customer/blog-list";
	}
	@GetMapping("/blog-detail")
	public String details() {
		return "customer/blog-detail";
	}
	@ModelAttribute("attr_user")
    public org.springframework.security.core.userdetails.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return (User) auth.getPrincipal();
        }
        return null;
    }
}
