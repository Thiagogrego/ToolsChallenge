package br.com.thiagogrego.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.thiagogrego.DTO.DescricaoRequest;
import br.com.thiagogrego.DTO.EstornoRequest;
import br.com.thiagogrego.DTO.EstornoResponse;
import br.com.thiagogrego.DTO.FormaPagamentoRequest;
import br.com.thiagogrego.DTO.TransacaoRequest;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.model.Descricao;
import br.com.thiagogrego.model.Estorno;
import br.com.thiagogrego.model.FormaPagamento;
import br.com.thiagogrego.model.Transacao;
import br.com.thiagogrego.repositores.DescricaoRepository;
import br.com.thiagogrego.repositores.EstornoRepository;
import br.com.thiagogrego.repositores.FormaPagamentoRepository;
import br.com.thiagogrego.repositores.TransacaoRepository;
import br.com.thiagogrego.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class EstornoServiceTest {

	@InjectMocks
	private EstornoService estornoService;

	@Mock
	private EstornoRepository estornoRepo;

	@Mock
	private TransacaoRepository transacaoRepo;

	@Mock
	private DescricaoRepository descricaoRepo;

	@Mock
	private FormaPagamentoRepository formaPagamentoRepo;

	private TransacaoRequest transacaoReq;
	private DescricaoRequest descricaoReq;
	private FormaPagamentoRequest formaPagamentoReq;
	private EstornoRequest estornoReq;

	private Transacao transacao;
	private Descricao descricao;
	private FormaPagamento formaPagamento;
	private Estorno estorno;

	private Optional<Estorno> estornoOpt;

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

	public static final String MSG_OBJECT_NOT_FOUND = "Objeto não encontrado! Id: ";

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		startEstorno();
	}

	@Test
	void receberUmEstornoRequestERetornarUmEstornoResponse() {
		Mockito.when(estornoRepo.save((Mockito.any()))).thenReturn(estorno);
		Mockito.when(transacaoRepo.save((Mockito.any()))).thenReturn(transacao);
		Mockito.when(descricaoRepo.save((Mockito.any()))).thenReturn(descricao);
		Mockito.when(formaPagamentoRepo.save((Mockito.any()))).thenReturn(formaPagamento);

		EstornoResponse response = estornoService.estorno(estornoReq);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(EstornoResponse.class, response.getClass());
	}

	@Test
	void buscarPorIdERetornarUmEstornoResponse() {
		Mockito.when(estornoRepo.findById(Mockito.anyString())).thenReturn(estornoOpt);

		EstornoResponse response = estornoService.findById("id");

		Assertions.assertNotNull(response);
		Assertions.assertEquals(EstornoResponse.class, response.getClass());
	}

	@Test
	void buscarPorIdERetornarUmaExecaoDeObjetoNaoEncontrado() {
		Mockito.when(estornoRepo.findById(Mockito.anyString()))
				.thenThrow(new ObjectNotFoundException(MSG_OBJECT_NOT_FOUND + id));

		try {
			estornoService.findById(id);
		} catch (Exception ex) {
			Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
			Assertions.assertEquals(MSG_OBJECT_NOT_FOUND + id, ex.getMessage());
		}
	}

	private void startEstorno() {

		descricao = new Descricao(id, valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
		formaPagamento = new FormaPagamento(id, tipo, parcelas);
		transacao = new Transacao(cartao, id, descricao, formaPagamento);
		estorno = new Estorno(id, transacao);

		descricaoReq = new DescricaoRequest(valor, dataHora, estabelecimento);
		formaPagamentoReq = new FormaPagamentoRequest(tipo, parcelas);
		transacaoReq = new TransacaoRequest(cartao, id, descricaoReq, formaPagamentoReq);
		estornoReq = new EstornoRequest(transacaoReq);

		estornoOpt = Optional.of(new Estorno(id, transacao));
	}

}
