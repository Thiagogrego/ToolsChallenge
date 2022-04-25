package br.com.thiagogrego.DTO;

public class PagamentoResponse {

	private TransacaoResponse transacao;

	public PagamentoResponse() {
	}

	public PagamentoResponse(TransacaoResponse transacao) {
		this.transacao = transacao;
	}

	public TransacaoResponse getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoResponse transacao) {
		this.transacao = transacao;
	}
}
