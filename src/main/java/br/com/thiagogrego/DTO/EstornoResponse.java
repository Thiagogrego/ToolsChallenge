package br.com.thiagogrego.DTO;

public class EstornoResponse {

	private TransacaoResponse transacao;

	public EstornoResponse() {
	}

	public EstornoResponse(TransacaoResponse transacao) {
		this.transacao = transacao;
	}

	public TransacaoResponse getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoResponse transacao) {
		this.transacao = transacao;
	}

}
