package com.mhj.math.data;

import java.math.BigDecimal;

import com.mhj.math.data.interfaces.Valor;

public class Decimal implements Valor{
	
	BigDecimal valor;

	public Decimal(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public String getHtml() {
		return this.valor.toString();
	}

	public static BigDecimal valueOf(String valor) {
		return new BigDecimal(valor);
	}

	public int compareTo(Decimal valor) {
		return this.valor.compareTo(valor.getValor());
	}

	public Descricao toDescricao() {
		return new Descricao(this.valor.toString());
	}

	@Override
	public String getTexto() {
		return this.valor.toString();
	}

	public BigDecimal getValorSemSinal() {
		return new BigDecimal(Math.abs(valor.doubleValue()));
	}

}
