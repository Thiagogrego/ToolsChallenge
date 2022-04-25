package br.com.thiagogrego.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagogrego.DTO.EstornoRequest;
import br.com.thiagogrego.DTO.EstornoResponse;
import br.com.thiagogrego.services.EstornoService;

@RestController
@RequestMapping("/api/estornos")
public class EstornoController {

	@Autowired
	private EstornoService estornoService;

	@PostMapping
	public ResponseEntity<EstornoResponse> estorno(@Valid @RequestBody EstornoRequest estornoRequest) {
		EstornoResponse estornoResponse = estornoService.estorno(estornoRequest);
		return ResponseEntity.ok(estornoResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstornoResponse> findById(@PathVariable String id) {
		EstornoResponse estornoResponse = estornoService.findById(id);
		return ResponseEntity.ok(estornoResponse);
	}

}
