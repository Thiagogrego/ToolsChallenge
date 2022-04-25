package br.com.thiagogrego.services;

import java.util.List;
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
import br.com.thiagogrego.DTO.FormaPagamentoRequest;
import br.com.thiagogrego.DTO.PagamentoRequest;
import br.com.thiagogrego.DTO.PagamentoResponse;
import br.com.thiagogrego.DTO.TransacaoRequest;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.model.Descricao;
import br.com.thiagogrego.model.FormaPagamento;
import br.com.thiagogrego.model.Pagamento;
import br.com.thiagogrego.model.Transacao;
import br.com.thiagogrego.repositores.DescricaoRepository;
import br.com.thiagogrego.repositores.FormaPagamentoRepository;
import br.com.thiagogrego.repositores.PagamentoRepository;
import br.com.thiagogrego.repositores.TransacaoRepository;
import br.com.thiagogrego.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class PagamentoServiceTest {

	@InjectMocks
	private PagamentoService pagamentoService;

	@Mock
	private PagamentoRepository pagamentoRepo;

	@Mock
	private TransacaoRepository transacaoRepo;

	@Mock
	private DescricaoRepository descricaoRepo;

	@Mock
	private FormaPagamentoRepository formaPagamentoRepo;

	private TransacaoRequest transacaoReq;
	private DescricaoRequest descricaoReq;
	private FormaPagamentoRequest formaPagamentoReq;
	private PagamentoRequest pagamentoReq;

	private Transacao transacao;
	private Descricao descricao;
	private FormaPagamento formaPagamento;
	private Pagamento pagamento;

	private Optional<Pagamento> pagamentoOpt;

	private String cartao = "1234567890123456";
	private String id = "1";
	private String valor = "500.00";
	private String dataHora = "19/04/2022 22:00:01";
	private String estabelecimento = "PetShop";
	private String nsu = "654321";
	private String codigoAutorizacao = "0987654321";
	private Status status = Status.AUTORIZADO;
	private String tipo = "AVISTA";
	private String parcelas = "1";

	public static final String MSG_OBJECT_NOT_FOUND = "Objeto não encontrado! Id: ";

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		startPagamento();
	}

	@Test
	void receberUmPagamentoRequestERetornarUmPagamentoResponse() {
		Mockito.when(pagamentoRepo.save((Mockito.any()))).thenReturn(pagamento);
		Mockito.when(transacaoRepo.save((Mockito.any()))).thenReturn(transacao);
		Mockito.when(descricaoRepo.save((Mockito.any()))).thenReturn(descricao);
		Mockito.when(formaPagamentoRepo.save((Mockito.any()))).thenReturn(formaPagamento);

		PagamentoResponse response = pagamentoService.pagamento(pagamentoReq);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(PagamentoResponse.class, response.getClass());
	}

	@Test
	void buscarTodosPagamentosERetornarUmaListaDePagamentoResponse() {
		Mockito.when(pagamentoRepo.findAll()).thenReturn(List.of(pagamento));

		List<PagamentoResponse> response = pagamentoService.findAll();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(PagamentoResponse.class, response.get(0).getClass());
	}

	@Test
	void buscarPorIdERetornarUmPagamentoResponse() {
		Mockito.when(pagamentoRepo.findById(Mockito.anyString())).thenReturn(pagamentoOpt);

		PagamentoResponse response = pagamentoService.findById("id");

		Assertions.assertNotNull(response);
		Assertions.assertEquals(PagamentoResponse.class, response.getClass());
	}

	@Test
	void buscarPorIdERetornarUmaExecaoDeObjetoNaoEncontrado() {
		Mockito.when(pagamentoRepo.findById(Mockito.anyString()))
				.thenThrow(new ObjectNotFoundException(MSG_OBJECT_NOT_FOUND + id));

		try {
			pagamentoService.findById(id);
		} catch (Exception ex) {
			Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
			Assertions.assertEquals(MSG_OBJECT_NOT_FOUND + id, ex.getMessage());
		}
	}

	private void startPagamento() {

		descricao = new Descricao(id, valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
		formaPagamento = new FormaPagamento(id, tipo, parcelas);
		transacao = new Transacao(cartao, id, descricao, formaPagamento);
		pagamento = new Pagamento(id, transacao);

		descricaoReq = new DescricaoRequest(valor, dataHora, estabelecimento);
		formaPagamentoReq = new FormaPagamentoRequest(tipo, parcelas);
		transacaoReq = new TransacaoRequest(cartao, id, descricaoReq, formaPagamentoReq);
		pagamentoReq = new PagamentoRequest(transacaoReq);

		pagamentoOpt = Optional.of(new Pagamento(id, transacao));
	}

}
