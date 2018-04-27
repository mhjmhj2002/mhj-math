package com.mhj.math.build;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;

public class MultiplicacaoBuild extends OperacaoBasicaBuild {

	public MultiplicacaoBuild() {
		super();
	}

	public MultiplicacaoBuild(Operacao operacao, MessageSource messageSource, Locale locale) {
		super(operacao, messageSource, locale);
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
