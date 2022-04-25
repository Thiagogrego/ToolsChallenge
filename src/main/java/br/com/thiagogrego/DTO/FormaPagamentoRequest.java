package br.com.thiagogrego.DTO;

import javax.validation.constraints.NotEmpty;

public class FormaPagamentoRequest {

	@NotEmpty
	private String tipo;
	
	@NotEmpty
	private String parcelas;

	public FormaPagamentoRequest() {
	}

	public FormaPagamentoRequest(String tipo, String parcelas) {
		this.tipo = tipo;
		this.parcelas = parcelas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

}
