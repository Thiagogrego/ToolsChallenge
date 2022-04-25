package br.com.thiagogrego.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Transacao implements Serializable {

	private String cartao;

	@Id
	private String id;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Descricao descricao;

	@OneToOne(cascade = CascadeType.PERSIST)
	private FormaPagamento formaPagamento;

	public Transacao() {
	}

	public Transacao(String cartao, String id, Descricao descricao, FormaPagamento formaPagamento) {
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

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao descricao) {
		this.descricao = descricao;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
