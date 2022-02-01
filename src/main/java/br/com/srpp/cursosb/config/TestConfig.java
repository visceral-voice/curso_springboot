package br.com.srpp.cursosb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.srpp.cursosb.services.DBService;
import br.com.srpp.cursosb.services.EmailService;
import br.com.srpp.cursosb.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
		
	@Bean
	public boolean instatiateDatabase() throws Exception {
		
		dbService.instatiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
