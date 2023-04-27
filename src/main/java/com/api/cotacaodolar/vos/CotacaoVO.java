package com.api.cotacaodolar.vos;

public class CotacaoVO {
	private Double cotacaoCompra;
	private Double cotacaoVenda;

	public CotacaoVO(Double cotacaoCompra, Double cotacaoVenda) {
		this.cotacaoCompra = cotacaoCompra;
		this.cotacaoVenda = cotacaoVenda;
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

}