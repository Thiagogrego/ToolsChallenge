package br.com.thiagogrego.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.thiagogrego.DTO.DescricaoRequest;
import br.com.thiagogrego.DTO.DescricaoResponse;
import br.com.thiagogrego.DTO.FormaPagamentoRequest;
import br.com.thiagogrego.DTO.FormaPagamentoResponse;
import br.com.thiagogrego.DTO.PagamentoRequest;
import br.com.thiagogrego.DTO.PagamentoResponse;
import br.com.thiagogrego.DTO.TransacaoRequest;
import br.com.thiagogrego.DTO.TransacaoResponse;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.services.PagamentoService;

public class PagamentoControllerTest {

	@InjectMocks
	private PagamentoController pagamentoController;

	@Mock
	private PagamentoService pagamentoService;

	private TransacaoRequest transacaoReq;
	private DescricaoRequest descricaoReq;
	private FormaPagamentoRequest formaPagamentoReq;
	private PagamentoRequest pagamentoReq;

	private TransacaoResponse transacaoRes;
	private DescricaoResponse descricaoRes;
	private FormaPagamentoResponse formaPagamentoRes;
	private PagamentoResponse pagamentoRes;

	private String cartao = "1234567890123456";
	private String id = "1";
	private String valor = "500.00";
	private String dataHora = "19/04/2022 22:00:01";
	private String estabelecimento = "PetShop";
	private String nsu = "654321";
	private String codigoAutorizacao = "0987654321";
	private Status status = Status.CANCELADO;
	private String tipo = "AVISTA";
	private String parcelas = "1";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		startPagamentos();
	}

	@Test
	void receberUmPagamentoRequestERetornarUmPagamentoResponse() {
		Mockito.when(pagamentoService.pagamento((Mockito.any()))).thenReturn(pagamentoRes);
		ResponseEntity<PagamentoResponse> response = pagamentoController.pagamento(pagamentoReq);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(PagamentoResponse.class, response.getBody().getClass());
	}
	
	@Test
	void retornarUmaListaDePagamentosResponse() {
		Mockito.when(pagamentoService.findAll()).thenReturn(List.of(pagamentoRes));
		
		ResponseEntity<List<PagamentoResponse>> response = pagamentoController.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(PagamentoResponse.class, response.getBody().get(0).getClass());
	}
	@Test
	void buscarPorIdERetornarUmPagamentoResponse() {
		Mockito.when(pagamentoService.findById(Mockito.anyString())).thenReturn(pagamentoRes);

		ResponseEntity<PagamentoResponse> response = pagamentoController.findById("id");

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(PagamentoResponse.class, response.getBody().getClass());
	}

	private void startPagamentos() {

		descricaoReq = new DescricaoRequest(valor, dataHora, estabelecimento);
		formaPagamentoReq = new FormaPagamentoRequest(tipo, parcelas);
		transacaoReq = new TransacaoRequest(cartao, id, descricaoReq, formaPagamentoReq);
		pagamentoReq = new PagamentoRequest(transacaoReq);

		descricaoRes = new DescricaoResponse(valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
		formaPagamentoRes = new FormaPagamentoResponse(tipo, parcelas);
		transacaoRes = new TransacaoResponse(cartao, id, descricaoRes, formaPagamentoRes);
		pagamentoRes = new PagamentoResponse(transacaoRes);

	}

}
