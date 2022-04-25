package br.com.thiagogrego.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagogrego.DTO.PagamentoRequest;
import br.com.thiagogrego.DTO.PagamentoResponse;
import br.com.thiagogrego.services.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	@PostMapping
	public ResponseEntity<PagamentoResponse> pagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest) {
		PagamentoResponse pagamentoResponse = pagamentoService.pagamento(pagamentoRequest);
		return ResponseEntity.ok(pagamentoResponse);
	}

	@GetMapping
	public ResponseEntity<List<PagamentoResponse>> findAll() {
		List<PagamentoResponse> pagamentos = pagamentoService.findAll();
		return ResponseEntity.ok(pagamentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoResponse> findById(@PathVariable String id) {
		PagamentoResponse pagamentoResponse = pagamentoService.findById(id);
		return ResponseEntity.ok(pagamentoResponse);
	}
}
