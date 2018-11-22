package com.mhj.math.build;

import java.util.List;

import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.operacao.Potenciacao;

public abstract class PotenciaBuild extends Build {

	List<Potenciacao> potencias;

	public PotenciaBuild() {
		super();
	}

	public PotenciaBuild(List<Potenciacao> potencias) {
		super();
		this.potencias = potencias;
	}

	public List<Potenciacao> getPotencias() {
		return potencias;
	}
	
	public void montaPotencia(Potenciacao potencia, boolean mostraSinal){
		montaPotencia(potencia, mostraSinal, true);
	}
	
	public void montaPotencia(Potenciacao potencia, boolean mostraSinal, boolean addNumerador){
		if (mostraSinal) {
			montaValor(potencia.getBase());
		}
	
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(potencia.getExpoente());
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
	}

}
