package br.com.srpp.cursosb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.srpp.cursosb.services.DBService;
import br.com.srpp.cursosb.services.EmailService;
import br.com.srpp.cursosb.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instatiateDatabase() throws Exception {

		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instatiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailServiceHTML() {
		return new SmtpEmailService();
	}

}
