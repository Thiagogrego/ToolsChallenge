package br.com.thiagogrego.DTO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransacaoResponse implements Serializable {

	private String cartao;

	private String id;

	private DescricaoResponse descricao;

	private FormaPagamentoResponse formaPagamento;

	public TransacaoResponse() {
	}

	public TransacaoResponse(String cartao, String id, DescricaoResponse descricao,
			FormaPagamentoResponse formaPagamento) {
		this.cartao = cartao;
		this.id = id;
		this.descricao = descricao;
		this.formaPagamento = formaPagamento;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DescricaoResponse getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoResponse descricao) {
		this.descricao = descricao;
	}

	public FormaPagamentoResponse getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoResponse formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
