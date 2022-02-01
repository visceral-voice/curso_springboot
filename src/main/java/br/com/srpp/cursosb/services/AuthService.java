package br.com.srpp.cursosb.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.srpp.cursosb.domain.Cliente;
import br.com.srpp.cursosb.repositories.ClienteRepository;
import br.com.srpp.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}

		String newPass = newPassWord();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassWord() {
		char[] vet = new char[10];
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 1) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} 
		else if (opt == 2) { //gera uma letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera uma letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
