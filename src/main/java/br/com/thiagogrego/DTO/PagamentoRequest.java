package br.com.thiagogrego.DTO;

import javax.validation.Valid;

public class PagamentoRequest {

	@Valid
	private TransacaoRequest transacao;

	public PagamentoRequest() {
	}

	public PagamentoRequest(TransacaoRequest transacao) {
		this.transacao = transacao;
	}

	public TransacaoRequest getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoRequest transacao) {
		this.transacao = transacao;
	}

}
