package com.mhj.math.build;

import java.util.Locale;

import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.MathProperties;
import com.mhj.math.util.MathjaxUtil;

public abstract class Build {
	
	Operacao operacao;

	Locale locale;

	public Build(Operacao operacao) {
		this.operacao = operacao;
	}

	public Operacao resolver() throws BusinessException, RegraException{
			validarOperacao();
			validarParametros();
			titulo();
			regras();
			resolucao();
		return this.operacao;
	}
	
	protected abstract void validarParametros() throws BusinessException, RegraException;
	
	protected abstract void titulo() throws BusinessException;
	
	protected abstract void regras() throws BusinessException, RegraException;
	
	protected abstract void resolucao() throws BusinessException, RegraException;

	private void validarOperacao() throws BusinessException {
		if (operacao == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.1"));
		}
		if (operacao.getRetorno() == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.2"));
		}
		if (operacao.getFiltroOperacoes() == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.3"));
		}
	}
	
	public void abreMath(){
		operacao.getRetorno().addAll(MathjaxUtil.abreMath());
	}
	
	public void fechaMath(){
		operacao.getRetorno().add(MathjaxTag.MATH_CLOSE);
	}
	
	public void fechaTable(){
		operacao.getRetorno().add(MathjaxTag.MATH_CLOSE);
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
