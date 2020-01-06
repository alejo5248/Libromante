package com.libromante.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.libromante.entity.Usuario;


@Service
public class EnvioEmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EnvioEmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(Usuario usuario) throws MailException {
		
		SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(usuario.getEmail());
        email.setFrom("alejo5248@gmail.com");
        email.setSubject("libromante notificacion");
        email.setText("su usuario ha sido creado exitosamente");
       
        javaMailSender.send(email);
		
	}

}
