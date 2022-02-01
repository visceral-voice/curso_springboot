package br.com.srpp.cursosb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.srpp.cursosb.domain.Cidade;
import br.com.srpp.cursosb.dto.CidadeDTO;
import br.com.srpp.cursosb.services.CidadeService;

@RestController
@RequestMapping(value="/estados")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@RequestMapping(value="/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> list(@PathVariable Integer estado_id) {
		
		List<Cidade> lista = service.findAll(estado_id);
		List<CidadeDTO> listDTO = lista.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

}
