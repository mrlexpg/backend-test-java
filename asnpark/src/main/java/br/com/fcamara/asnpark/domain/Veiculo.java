package br.com.fcamara.asnpark.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fcamara.asnpark.error.validator.PlacaVeiculo;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_VEICULO")
public class Veiculo extends AbstractEntity<Long> {

	@PlacaVeiculo
	@NotEmpty(message = "Por favor, insira a placa do veiculo.")
	@Column(name="ds_placa",nullable = false, unique = true, length = 8)
	public String placa;

	@NotEmpty(message = "Por favor, insira a marca do veiculo.")
	@Column(name="ds_marca",nullable = false, length = 10)
    public String marca;
    
	@NotEmpty(message = "Por favor, insira o modelo do veiculo.")
	@Column(name="ds_modelo",nullable = false, length = 15)
    public String modelo;
    
	@NotEmpty(message = "Por favor, insira a cor do veiculo.")
	@Column(name="ds_cor",nullable = false, length = 10)
    public String cor;
      
	@NotEmpty(message = "Por favor, insira ao tipo do veiculo (Carro / Moto).")
	@Column(name="ds_tipo",nullable = false, length = 5)
    public String tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "veiculo")
	private List<EntradaSaidaVeiculo> entradaSaidaVeiculos;
	
	public Veiculo daMarca(String marca) {
		this.marca = marca;
		return this;
	}
	
	public Veiculo modelo(String modelo) {
		this.modelo = modelo;
		return this;
	}
	
	public Veiculo daCor(String nomeCor) {
		this.cor = nomeCor;
		return this;
	}
	
	public Veiculo placa(String descricao) {
		this.placa = descricao;
		return this;
	}
	
	public Veiculo doTipo (String tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<EntradaSaidaVeiculo> getEntradaSaidaVeiculos() {
		return entradaSaidaVeiculos;
	}

	public void setEntradaSaidaVeiculos(List<EntradaSaidaVeiculo> entradaSaidaVeiculos) {
		this.entradaSaidaVeiculos = entradaSaidaVeiculos;
	}
}