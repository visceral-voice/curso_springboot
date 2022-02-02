package br.com.srpp.cursosb.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.srpp.cursosb.services.EmailService;
import br.com.srpp.cursosb.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	 @Bean
	 public boolean instantiateDataBase() throws ParseException {
	 return true;
	 }
	 
	 @Bean
	 public EmailService emailService() {
	 return new SmtpEmailService();
	 }	
	
}
