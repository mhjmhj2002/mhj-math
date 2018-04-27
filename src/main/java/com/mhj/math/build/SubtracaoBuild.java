package com.mhj.math.build;

import org.springframework.context.MessageSource;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;

public class SubtracaoBuild extends OperacaoBasicaBuild {

	public SubtracaoBuild() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubtracaoBuild(Operacao operacao, MessageSource messageSource) {
		super(operacao, messageSource);
		// TODO Auto-generated constructor stub
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
