package com.mhj.math.build;

import java.util.List;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;

public class FracaoSubtracaoBuild extends FracaoBuild {

	public FracaoSubtracaoBuild() {
		super();
	}

	protected FracaoSubtracaoBuild(List<Fracao> fracoes) {
		super(fracoes);
	}

	@Override
	protected void validarParametros() throws BusinessException {
		
	}

	@Override
	protected void titulo() throws BusinessException {
		
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		
	}

}
