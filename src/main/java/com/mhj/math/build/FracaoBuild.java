package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.OperacaoUtil;

public abstract class FracaoBuild extends Build{
	
	List<Fracao> fracoes;
	
	public FracaoBuild(){
		super(new Operacao(new ArrayList<>(), new ArrayList<>()), null);
	}
	
	protected FracaoBuild(List<Fracao> fracoes, Operacao operacao, MessageSource messageSource) {
		super(operacao, messageSource);
		this.fracoes = fracoes;
	}
	
	@Override
	protected abstract void validarParametros() throws BusinessException, RegraException;

	public List<Fracao> getFracoes() {
		return fracoes;
	}
	
	public void montaFracao(List<Data> nominadores, List<Data> denominadores){
		operacao.getRetorno().add(Simbolo.MENOR);
		operacao.getRetorno().add(new Descricao("MATH"));
		operacao.getRetorno().add(MathjaxProperty.XMLNS);
		operacao.getRetorno().add(MathjaxProperty.DISPLAY);
		operacao.getRetorno().add(Simbolo.MAIOR);
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_OPEN);
	
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MI_OPEN);
		operacao.getRetorno().add(Letra.a);
		operacao.getRetorno().add(MathjaxTag.MI_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MI_OPEN);
		operacao.getRetorno().add(Letra.a);
		operacao.getRetorno().add(MathjaxTag.MI_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_CLOSE);

		operacao.getRetorno().add(MathjaxTag.MATH_CLOSE);
	}
	
	public void montaFracao(Data numerador, Data denominador){
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_OPEN);
	
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(numerador);
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(denominador);
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_CLOSE);
	}
	
	public void montaFracao(Fracao fracao, boolean mostraSinal){
		montaFracao(fracao, mostraSinal, true);
	}
	
	public void montaFracao(Fracao fracao, boolean mostraSinal, boolean addNumerador){
		if (mostraSinal) {
			montaValor(fracao.getNumerador());
		}
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_OPEN);
	
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		if (addNumerador) {
			operacao.getRetorno().add(fracao.getNumerador().getValorSemSinal());
		}
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(fracao.getDenominador());
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MFRAC_CLOSE);
	}
	
	private void montaValor(Inteiro numero){
		operacao.getRetorno().add(MathjaxTag.MO_OPEN);
		operacao.getRetorno().add(OperacaoUtil.getSinalNumero(numero));
		operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
	}

	protected void exibirFracoes(List<Fracao> fracoes) {
		Fracao fracao = fracoes.get(0);
		
		abreMath();

		if (fracao.getNumerador().getValor() < 0) {
			montaFracao(fracao, true);
		}else{
			montaFracao(fracao, false);
		}
		
		for (int i = 1; i < fracoes.size(); i++) {
			fracao = fracoes.get(i);
			montaFracao(fracao, true);
			
		}
		
		fechaMath();
	}

	public void setFracoes(List<Fracao> fracoes) {
		this.fracoes = fracoes;
	}

}
