package br.com.thiagogrego.DTO;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
public class TransacaoRequest implements Serializable {

	@NotEmpty(message="O número do cartão é obrigatório")
	@Length(min=16, max=16, message="O cartão deve conter 16 números")
	private String cartao;

	@NotEmpty(message="O número do id da transação é obrigatório")
	private String id;
	
	@Valid
	private DescricaoRequest descricao;

	@Valid
	private FormaPagamentoRequest formaPagamento;

	public TransacaoRequest() {
	}

	public TransacaoRequest(String cartao, String id, DescricaoRequest descricao,
			FormaPagamentoRequest formaPagamento) {
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

	public DescricaoRequest getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoRequest descricao) {
		this.descricao = descricao;
	}

	public FormaPagamentoRequest getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoRequest formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	
}
