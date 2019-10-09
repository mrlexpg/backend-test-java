package br.com.fcamara.asnpark.domain;

public enum EstadoUsuario {

	Desativo(0,"Desativo"),
	Ativo(1,"Ativo");
	
	private Integer codigo;
	
	private String descricao;

	EstadoUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
