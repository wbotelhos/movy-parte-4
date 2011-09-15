package br.com.wbotelhos.movy.model.common;

public enum TipoSexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String label;

	private TipoSexo(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}