package br.com.srpp.cursosb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.srpp.cursosb.domain.Estado;
import br.com.srpp.cursosb.dto.EstadoDTO;
import br.com.srpp.cursosb.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Integer id) {
		
		Estado obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> list() {

		List<Estado> listaEstados = service.findAll();
		List<EstadoDTO> listDTO = listaEstados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
}
