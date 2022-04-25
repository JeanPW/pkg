package com.api.pkg.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepartamentoDto {

	@NotBlank
	private String nomeDepart;
	@NotNull
	private double orcamento;
	@NotBlank
	private String ramal;
	
	
	public String getNomeDepart() {
		return nomeDepart;
	}
	public void setNomeDepart(String nomeDepart) {
		this.nomeDepart = nomeDepart;
	}
	public double getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	
}
