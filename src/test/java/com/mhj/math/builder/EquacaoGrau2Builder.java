package com.mhj.math.builder;

import com.mhj.math.data.Descricao;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.operacao.EquacaoGrau2;

public class EquacaoGrau2Builder {
	
	private EquacaoGrau2 equacaoGrau2;
	
	public static EquacaoGrau2Builder create() {
		return new EquacaoGrau2Builder();
	}
	
	public EquacaoGrau2 getEquacaoOk() {
		equacaoGrau2 = new EquacaoGrau2(Letra.x, new Descricao("1"), new Descricao("2"), new Descricao("3"), Sinal.POSITIVO, Sinal.NEGATIVO, Sinal.NEGATIVO, null, null);
		return equacaoGrau2;
	}

}
