package com.mhj.math.build;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.Operando;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.OperacaoUtil;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FracaoBuildMultiplicacao extends FracaoBuild {
	
	@Autowired
	FracaoSimplificacaoBuild fracaoSimplificacaoBuild;
	
	public FracaoBuildMultiplicacao(){
		super();
	}

	public FracaoBuildMultiplicacao(List<Fracao> fracoes, Operacao operacao) {
		super(fracoes, operacao);
	}

	@Override
	protected void validarParametros() throws BusinessException {
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao("Resolvendo "));
		
		exibirFracoes(fracoes);
		
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		operacao.getRetorno().add(new Descricao("Multiplicando numeradores: "));
		Inteiro multiplicacaoNumeradores = multiplicarNumeradores();

		operacao.getRetorno().add(new Descricao("Multiplicando denominadores: "));
		Inteiro multiplicacaoDenominadores = multiplicarDenominadores();
		
		fracaoSimplificacaoBuild = new FracaoSimplificacaoBuild(new Fracao(multiplicacaoNumeradores, multiplicacaoDenominadores), operacao);
		
		fracaoSimplificacaoBuild.resolver();
	}

	private Inteiro multiplicarDenominadores() {
		Inteiro retorno = new Inteiro(1);
		for (Fracao fracao : fracoes) {
			retorno = OperacaoUtil.multiplicacao(retorno, fracao.getDenominador());
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}

	private Inteiro multiplicarNumeradores() {
		Inteiro retorno = new Inteiro(1);
		for (Fracao fracao : fracoes) {
			retorno = OperacaoUtil.multiplicacao(retorno, fracao.getNumerador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}	 

}
