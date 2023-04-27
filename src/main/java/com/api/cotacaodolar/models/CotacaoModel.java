package com.api.cotacaodolar.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_COTACAO")
public class CotacaoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3435042569370800512L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;
	
	@Column(name = "data_cotacao", nullable = false)
	private LocalDate dataCotacao;
	
	@Column(name = "cotacao_compra", nullable = false)
	private Double cotacaoCompra;
	
	@Column(name = "cotacao_venda", nullable = false)
	private Double cotacaoVenda;
	
	public CotacaoModel() {
		
	}

	public CotacaoModel(LocalDateTime dataCadastro, LocalDate dataCotacao, Double cotacaoCompra, Double cotacaoVenda) {
		this.dataCadastro = dataCadastro;
		this.dataCotacao = dataCotacao;
		this.cotacaoCompra = cotacaoCompra;
		this.cotacaoVenda = cotacaoVenda;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(LocalDate dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public Double getCotacaoCompra() {
		return cotacaoCompra;
	}

	public void setCotacaoCompra(Double cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}

	public Double getCotacaoVenda() {
		return cotacaoVenda;
	}

	public void setCotacaoVenda(Double cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}

	public UUID getId() {
		return id;
	}

}
