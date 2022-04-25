package br.com.thiagogrego.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagogrego.DTO.PagamentoRequest;
import br.com.thiagogrego.DTO.PagamentoResponse;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.model.Descricao;
import br.com.thiagogrego.model.Pagamento;
import br.com.thiagogrego.model.Transacao;
import br.com.thiagogrego.repositores.DescricaoRepository;
import br.com.thiagogrego.repositores.FormaPagamentoRepository;
import br.com.thiagogrego.repositores.PagamentoRepository;
import br.com.thiagogrego.repositores.TransacaoRepository;
import br.com.thiagogrego.services.exceptions.DataIntegrityException;
import br.com.thiagogrego.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepo;

	@Autowired
	private DescricaoRepository descricaoRepo;

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepo;

	@Autowired
	private TransacaoRepository transacaoRepo;
	
	public static final String NSU = "1234567";
	public static final String CODIGO_AUTORIZACAO = "7654321";
	public static final String MSG_OBJECT_NOT_FOUND = "Objeto não encontrado! Id: ";
	public static final String MSG_DATA_INTEGRITY = "Transação com o Id existente! Id ";
	
	public PagamentoResponse pagamento(PagamentoRequest pagamentoRequest) {
		Pagamento pagamento = new ModelMapper().map(pagamentoRequest, Pagamento.class);

		findByTransacao(pagamento);

		Descricao descricao = pagamento.getTransacao().getDescricao();
		descricao.setNsu(NSU);
		descricao.setCodigoAutorizacao(CODIGO_AUTORIZACAO);
		descricao.setStatus(Status.AUTORIZADO);

		pagamentoRepo.save(pagamento);
		transacaoRepo.save(pagamento.getTransacao());
		descricaoRepo.save(descricao);
		formaPagamentoRepo.save(pagamento.getTransacao().getFormaPagamento());

		PagamentoResponse pagamentoResponse = new ModelMapper().map(pagamento, PagamentoResponse.class);
		return pagamentoResponse;
	}

	public List<PagamentoResponse> findAll() {
		List<Pagamento> pagamentos = pagamentoRepo.findAll();
		return pagamentos.stream().map(pagamento -> new ModelMapper().map(pagamento, PagamentoResponse.class))
				.collect(Collectors.toList());
	}

	public PagamentoResponse findById(String id) {
		Optional<Pagamento> pagamento = pagamentoRepo.findById(id);

		if (pagamento.isEmpty()) {
			throw new ObjectNotFoundException(MSG_OBJECT_NOT_FOUND + id);
		}
		PagamentoResponse pagamentoResponse = new ModelMapper().map(pagamento.get(), PagamentoResponse.class);
		return pagamentoResponse;
	}

	private void findByTransacao(Pagamento pagamento) {
		Optional<Transacao> transacao = transacaoRepo.findById(pagamento.getTransacao().getId());

		if (transacao.isPresent()) {
			throw new DataIntegrityException(MSG_DATA_INTEGRITY + transacao.get().getId());
		}
	}
	
}
