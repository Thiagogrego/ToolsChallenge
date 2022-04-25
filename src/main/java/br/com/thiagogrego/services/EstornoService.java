package br.com.thiagogrego.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagogrego.DTO.EstornoRequest;
import br.com.thiagogrego.DTO.EstornoResponse;
import br.com.thiagogrego.Enums.Status;
import br.com.thiagogrego.model.Descricao;
import br.com.thiagogrego.model.Estorno;
import br.com.thiagogrego.model.Transacao;
import br.com.thiagogrego.repositores.DescricaoRepository;
import br.com.thiagogrego.repositores.EstornoRepository;
import br.com.thiagogrego.repositores.FormaPagamentoRepository;
import br.com.thiagogrego.repositores.TransacaoRepository;
import br.com.thiagogrego.services.exceptions.DataIntegrityException;
import br.com.thiagogrego.services.exceptions.ObjectNotFoundException;

@Service
public class EstornoService {

	@Autowired
	private EstornoRepository estornoRepo;

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
	
	public EstornoResponse estorno(EstornoRequest estornoRequest) {
		
		Estorno estorno = new ModelMapper().map(estornoRequest, Estorno.class);
		
		findByTransacao(estorno);
		
		Descricao descricao = estorno.getTransacao().getDescricao();
		descricao.setNsu(NSU);
		descricao.setCodigoAutorizacao(CODIGO_AUTORIZACAO);
		descricao.setStatus(Status.CANCELADO);

		estornoRepo.save(estorno);
		transacaoRepo.save(estorno.getTransacao());
		descricaoRepo.save(descricao);
		formaPagamentoRepo.save(estorno.getTransacao().getFormaPagamento());

		return new ModelMapper().map(estorno, EstornoResponse.class);


	}

	public EstornoResponse findById(String id) {
		Optional<Estorno> estorno = estornoRepo.findById(id);

		if (estorno.isEmpty()) {
			throw new ObjectNotFoundException(MSG_OBJECT_NOT_FOUND + id);
		}

		return new ModelMapper().map(estorno.get(), EstornoResponse.class);
	}
		
	private void findByTransacao(Estorno estorno) {
		Optional<Transacao> transacao = transacaoRepo.findById(estorno.getTransacao().getId());
		
		if(transacao.isPresent()) {
			throw new DataIntegrityException(MSG_DATA_INTEGRITY + transacao.get().getId());
		}
	}
}
