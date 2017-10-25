package com.mhj.math.build;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.OperacaoUtil;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FracaoBuildDivisao extends FracaoBuild {
	
	@Autowired
	FracaoSimplificacaoBuild fracaoSimplificacaoBuild;
	
	public FracaoBuildDivisao(){
		super();
	}

	public FracaoBuildDivisao(List<Fracao> fracoes, Operacao operacao) {
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
		operacao.getRetorno().add(new Descricao("Para dividir frações multiplicamos a primeira fração pelo inverso da segunda"));
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		Fracao resultado = fracoes.get(0);
		for (int i = 1; i < fracoes.size(); i++) {
			Fracao fracao = fracoes.get(i);
			
			abreMath();

			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(new Descricao("Invertendo fração de "));
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			
			montaFracao(fracao.getNumerador(), fracao.getDenominador());
			
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(new Descricao(" para "));
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			
			montaFracao(fracao.getDenominador(), fracao.getNumerador());
			
			fechaMath();
			
			fracao = new Fracao(fracao.getDenominador(), fracao.getNumerador());
			operacao.getRetorno().add(new Descricao("Multiplicando numeradores: "));
			Inteiro multiplicacaoNumeradores = multiplicarNumeradores(resultado, fracao);

			operacao.getRetorno().add(new Descricao("Multiplicando denominadores: "));
			Inteiro multiplicacaoDenominadores = multiplicarDenominadores(resultado, fracao);
			
			resultado = new Fracao(multiplicacaoNumeradores, multiplicacaoDenominadores);
			
			abreMath();
			
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(new Descricao("Resultado:"));
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			
			montaFracao(resultado.getNumerador(), resultado.getDenominador());
			
			fechaMath();
		}
		
		fracaoSimplificacaoBuild = new FracaoSimplificacaoBuild(resultado, operacao);
		
		fracaoSimplificacaoBuild.resolver();
	}

	private Inteiro multiplicarNumeradores(Fracao fracao1, Fracao fracao2) {
		Inteiro retorno = new Inteiro(1);
		retorno = OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getNumerador());
		operacao.getRetorno().add(fracao1.getNumerador());
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(fracao2.getNumerador());
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(retorno);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}	 

	private Inteiro multiplicarDenominadores(Fracao fracao1, Fracao fracao2) {
		Inteiro retorno = new Inteiro(1);
		retorno = OperacaoUtil.multiplicacao(fracao1.getDenominador(), fracao2.getDenominador());
		operacao.getRetorno().add(fracao1.getDenominador());
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(fracao2.getDenominador());
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(retorno);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}

}
