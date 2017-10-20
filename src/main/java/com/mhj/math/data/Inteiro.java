package com.mhj.math.data;

import com.mhj.math.data.interfaces.Valor;

public class Inteiro implements Valor, Comparable<Inteiro>{
	
	Integer valor;

	public Inteiro(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	@Override
	public String getHtml() {
		return this.valor.toString();
	}

	public static Inteiro valueOf(String valor) {
		return new Inteiro(Integer.valueOf(valor));
	}

	public Descricao toDescricao() {
		return new Descricao(this.valor.toString());
	}

	@Override
	public String getTexto() {
		return this.valor.toString();
	}

	public Inteiro getValorSemSinal() {
		return new Inteiro(Math.abs(valor));
	}

	@Override
	public int compareTo(Inteiro valor) {
		return this.valor.compareTo(valor.getValor());
	}

}
