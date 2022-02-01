package br.com.srpp.cursosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.srpp.cursosb.domain.Estado;
import br.com.srpp.cursosb.dto.EstadoDTO;
import br.com.srpp.cursosb.repositories.EstadoRepository;
import br.com.srpp.cursosb.services.exceptions.DataIntegrityException;
import br.com.srpp.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}

	public Estado insert(Estado obj) {
		obj.setId(null);

		return repo.save(obj);
	}

	public Estado update(Estado obj) {
		Estado newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir um estado que possui cidades!");
		}

	}

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}

	public Estado fromDTO(EstadoDTO objDTO) {
		return new Estado(objDTO.getId(), objDTO.getNome());
	}

	private void updateData(Estado newObj, Estado obj) {
		newObj.setNome(obj.getNome());
	}
}
