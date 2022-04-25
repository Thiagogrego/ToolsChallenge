package br.com.thiagogrego.DTO;

import javax.validation.Valid;

public class EstornoRequest {

	@Valid
	private TransacaoRequest transacao;

	public EstornoRequest() {
	}

	public EstornoRequest(TransacaoRequest transacao) {
		this.transacao = transacao;
	}

	public TransacaoRequest getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoRequest transacao) {
		this.transacao = transacao;
	}
}
