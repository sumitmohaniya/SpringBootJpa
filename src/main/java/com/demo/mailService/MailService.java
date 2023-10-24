package com.demo.mailService;



import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailService {

	@Autowired
	private TemplateEngine templateEngine;
	
	
	 // @Autowired private JavaMailSender sender;
	 
	
	public boolean sendScheduleHtmlMail(String toAddress,String heading,Context context,String template,String formAddress) {
		
		Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");		
		  JavaMailSenderImpl sesSender = new JavaMailSenderImpl();
		  sesSender.setHost("smtp.gmail.com"); 
		  sesSender.setPort(587);
		  sesSender.setUsername("");
		  sesSender.setPassword(""); 
		  sesSender.setProtocol("smtp");
		  sesSender.setJavaMailProperties(props);
		  String html =templateEngine.process(template, context);
		  MimeMessage mail =sesSender.createMimeMessage(); 
		  try {
			  MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			  mail.setFrom(formAddress);
			  helper.setTo("");
			  helper.setSubject(heading);
			  helper.setText(html,true); 
			  sesSender.send(mail);
		  } catch(Exception e) {
			  e.printStackTrace(); 
		}
		 
		
		/*
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * message.setFrom(formAddress); message.setTo("sumitgupta11197@gmail.com");
		 * message.setSubject(heading); message.setText("sumit"); sender.send(message);
		 */
		//mailNotificationService.saveEmailRecord(heading,formAddress,toAddress);
		return true;
	}
}
