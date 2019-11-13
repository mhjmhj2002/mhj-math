package com.mhj.math.models;

import java.util.ArrayList;
import java.util.List;

public class Potencia {

	private Integer id;
	private Integer value;

	public Potencia(Integer id, Integer value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public static List<Potencia> getPotenciaList() {
		List<Potencia> retorno = new ArrayList<Potencia>();

		for (int i = 1; i < 100; i++) {
			Potencia potencia = new Potencia(i, i);
			retorno.add(potencia);
		}

		return retorno;
	}

}
