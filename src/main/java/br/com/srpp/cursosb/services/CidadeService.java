package br.com.srpp.cursosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.srpp.cursosb.domain.Cidade;
import br.com.srpp.cursosb.dto.CidadeDTO;
import br.com.srpp.cursosb.repositories.CidadeRepository;
import br.com.srpp.cursosb.services.exceptions.DataIntegrityException;
import br.com.srpp.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	public Cidade insert(Cidade obj) {
		obj.setId(null);

		return repo.save(obj);
	}

	public Cidade update(Cidade obj) {
		Cidade newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma cidade que possui endereço!");
		}

	}

	public List<Cidade> findAll(Integer estado) {
		return repo.findCidades(estado);
	}

	public Cidade fromDTO(CidadeDTO objDTO) {
		return new Cidade(objDTO.getId(), objDTO.getNome(), null);
	}

	private void updateData(Cidade newObj, Cidade obj) {
		newObj.setNome(obj.getNome());
		newObj.setEstado(obj.getEstado());
	}
}
