package com.mhj.math.build;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;

public class FracaoSubtracaoBuild extends FracaoBuild {

	public FracaoSubtracaoBuild() {
		super();
	}

	protected FracaoSubtracaoBuild(List<Fracao> fracoes, Operacao operacao, MessageSource messageSource, Locale locale) {
		super(fracoes, operacao, messageSource, locale);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void validarParametros() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void titulo() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		// TODO Auto-generated method stub
		
	}

}
