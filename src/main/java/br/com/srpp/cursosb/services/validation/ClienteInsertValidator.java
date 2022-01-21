package br.com.srpp.cursosb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.srpp.cursosb.domain.Cliente;
import br.com.srpp.cursosb.domain.enums.TipoCliente;
import br.com.srpp.cursosb.dto.ClienteNewDTO;
import br.com.srpp.cursosb.repositories.ClienteRepository;
import br.com.srpp.cursosb.resources.exceptions.FieldMessage;
import br.com.srpp.cursosb.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Autowired
	ClienteRepository repo;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		if (objDto.getTipo() == 0) {
			list.add(new FieldMessage("tipo", "Campo é obrigatório"));
		}
		
		if (TipoCliente.toEnum(objDto.getTipo()) == TipoCliente.PESSOAFISICA && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (TipoCliente.toEnum(objDto.getTipo()) == TipoCliente.PESSOAJURIDICA && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail já inserido no banco!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getfieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
