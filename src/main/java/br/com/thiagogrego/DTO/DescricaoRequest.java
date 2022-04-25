package br.com.thiagogrego.DTO;

import javax.validation.constraints.NotEmpty;

public class DescricaoRequest {
	
	@NotEmpty
	private String valor;
	
	@NotEmpty
	private String dataHora;
	
	@NotEmpty
	private String estabelecimento;

	public DescricaoRequest() {
	}

	public DescricaoRequest(String valor, String dataHora, String estabelecimento) {
		this.valor = valor;
		this.dataHora = dataHora;
		this.estabelecimento = estabelecimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

}
