package br.com.fcamara.asnpark.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_ENTRADA_SAIDA_VEICULO")
public class EntradaSaidaVeiculo extends AbstractEntity<Long> {
	
	@NotNull(message = "Por favor, insira a data e hora da entrada do veiculo")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "dt_hora_entrada", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime dataHoraEntrada;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "dt_hora_saida", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataHoraSaida;

	@NotNull(message = "Por favor, insira o ID do veiculo ja cadastrado.")
	@ManyToOne
	@JoinColumn(name = "id_veiculo_fk")
	private Veiculo veiculo;

	@NotNull(message = "Por favor, insira o ID do estabelecimento ja cadastrado.")
	@ManyToOne
	@JoinColumn(name="id_estabelecimento_fk")
	private Estabelecimento estabelecimento;
	
	
	public EntradaSaidaVeiculo paraEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
		return this;
	}
	
	public EntradaSaidaVeiculo comVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
		return this;
	}
	
	public EntradaSaidaVeiculo entrouNaDataHora(String data_hora_entrada) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");	
		
		LocalDateTime parsedDate = LocalDateTime.parse(data_hora_entrada, formatter);
				
		this.dataHoraEntrada = parsedDate;	
		
		return this;
	}
	
	public EntradaSaidaVeiculo saiuNaDataHora(String data_hora_saida) {
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");	
		
		LocalDateTime parsedDate = LocalDateTime.parse(data_hora_saida, formatter);
		
		this.dataHoraSaida = parsedDate;	
		
		return this;
	}

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}
