package br.com.thiagogrego.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.thiagogrego.DTO.DescricaoRequest;
import br.com.thiagogrego.DTO.DescricaoResponse;
import br.com.thiagogrego.DTO.EstornoRequest;
import br.com.thiagogrego.DTO.EstornoResponse;
import br.com.thiagogrego.DTO.FormaPagamentoRequest;
import br.com.thiagogrego.DTO.FormaPagamentoResponse;
import br.com.thiagogrego.DTO.TransacaoRequest;
import br.com.thiagogrego.DTO.TransacaoResponse;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.services.EstornoService;

@SpringBootTest
public class EstornoControllerTest {

	@InjectMocks
	private EstornoController estornoController;

	@Mock
	private EstornoService estornoService;

	private TransacaoRequest transacaoReq;
	private DescricaoRequest descricaoReq;
	private FormaPagamentoRequest formaPagamentoReq;
	private EstornoRequest estornoReq;

	private TransacaoResponse transacaoRes;
	private DescricaoResponse descricaoRes;
	private FormaPagamentoResponse formaPagamentoRes;
	private EstornoResponse estornoRes;

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
		startEstorno();
	}

	@Test
	void receberUmEstornoRequesteERetornarUmEstornoResponse() {
		Mockito.when(estornoService.estorno((Mockito.any()))).thenReturn(estornoRes);
		ResponseEntity<EstornoResponse> response = estornoController.estorno(estornoReq);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(EstornoResponse.class, response.getBody().getClass());
	}

	@Test
	void buscarPorIdERetornarUmEstornoResponse() {
		Mockito.when(estornoService.findById(Mockito.anyString())).thenReturn(estornoRes);

		ResponseEntity<EstornoResponse> response = estornoController.findById("id");

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(EstornoResponse.class, response.getBody().getClass());
	}

	private void startEstorno() {

		descricaoReq = new DescricaoRequest(valor, dataHora, estabelecimento);
		formaPagamentoReq = new FormaPagamentoRequest(tipo, parcelas);
		transacaoReq = new TransacaoRequest(cartao, id, descricaoReq, formaPagamentoReq);
		estornoReq = new EstornoRequest(transacaoReq);

		descricaoRes = new DescricaoResponse(valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
		formaPagamentoRes = new FormaPagamentoResponse(tipo, parcelas);
		transacaoRes = new TransacaoResponse(cartao, id, descricaoRes, formaPagamentoRes);
		estornoRes = new EstornoResponse(transacaoRes);
	}

}
