package br.com.fcamara.asnpark.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fcamara.asnpark.error.validator.CnpjEstabelecimento;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_ESTABELECIMENTO")
public class Estabelecimento extends AbstractEntity<Long> {

	@NotEmpty(message = "Por favor, insira a razao social.")
	@Column(name="ds_razao_social",nullable = false, length = 60)
	private String nome;
	
	@NotEmpty(message = "Por favor, insira o logradouro do estabelecimento.")
	@Column(name="ds_logradouro" ,nullable = false, length = 50)
	private String endereco;
	
	@NotNull(message = "Por favor, insira o numero do estabelecimento.")
	@Column(name="nm_logradouro", nullable = false)
	private Integer numeroLogradouro;
		
	@CnpjEstabelecimento
	@NotNull(message = "Por favor, insira o CNPJ do estabelecimento.")
	@Column(name="nm_cnpj", nullable = false, length = 14)
	private String cnpj; 	

	@NotNull(message = "Por favor, insira o DDD.")
	@Column(name="cd_ddd", nullable=false)
	private Integer codigoDDD;
	
	@NotNull(message = "Por favor, insira o Telefone do estabelecimento.")
	@Column(name="nm_telefone", nullable=false)
	private Integer numeroTelefone;

	@NotNull(message = "Por favor, insira a quantidade de vagas para moto.")
	@Column(name="qt_moto", nullable=false)
	private Integer quantidadeVagaMoto;

	@NotNull(message = "Por favor, insira a quantidade de vagas para carro.")
	@Column(name="qt_carro", nullable=false)
	private Integer quantidadeVagaCarro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "estabelecimento")
	private List<EntradaSaidaVeiculo> entradaSaidaVeiculos;
	
	
	
	public Estabelecimento comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Estabelecimento noEnderecoENumero(String endereco, Integer numero) {
		this.endereco = endereco;
		this.numeroLogradouro = numero;
		return this;
	}
	
	public Estabelecimento inscritoNoCNPJ(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	
	public Estabelecimento ddd_telefone(Integer ddd, Integer numero) {
		this.codigoDDD = ddd;
		this.numeroTelefone = numero;
		return this;
	}
	
	public Estabelecimento comQuantidadeVagaMoto(Integer quantidade) {
		this.quantidadeVagaMoto = quantidade;
		return this;
	}
	
	public Estabelecimento comQuantidadeVagaCarro(Integer quantidade) {
		this.quantidadeVagaCarro = quantidade;
		return this;
	}
	
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(Integer numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(Integer codigoDDD) {
		this.codigoDDD = codigoDDD;
	}

	public Integer getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(Integer numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Integer getQuantidadeVagaMoto() {
		return quantidadeVagaMoto;
	}

	public void setQuantidadeVagaMoto(Integer quantidadeVagaMoto) {
		this.quantidadeVagaMoto = quantidadeVagaMoto;
	}

	public Integer getQuantidadeVagaCarro() {
		return quantidadeVagaCarro;
	}

	public void setQuantidadeVagaCarro(Integer quantidadeVagaCarro) {
		this.quantidadeVagaCarro = quantidadeVagaCarro;
	}

	public List<EntradaSaidaVeiculo> getEntradaSaidaVeiculos() {
		return entradaSaidaVeiculos;
	}

	public void setEntradaSaidaVeiculos(List<EntradaSaidaVeiculo> entradaSaidaVeiculos) {
		this.entradaSaidaVeiculos = entradaSaidaVeiculos;
	}
}