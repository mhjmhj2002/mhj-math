package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.OperacaoUtil;

public class FracaoBuildSoma extends FracaoBuild {
	
	MMCBuild mmcBuild;
	FracaoSimplificacaoBuild fracaoSimplificacaoBuild;

	public FracaoBuildSoma(List<Fracao> fracoes, Operacao operacao) {
		super(fracoes, operacao);
		carregarMmcBuild();
	}

	@Override
	protected void titulo() {
		operacao.getRetorno().add(new Descricao("Resolvendo "));
		
		exibirFracoes(fracoes);
		
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	@Override
	protected void regras() throws RegraException, BusinessException {
		validarDenominadorIgual();		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		mmcBuild.resolver();

		operacao.getRetorno().add(new Descricao("Para encontrar o numerador devemos:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao("- Dividir o MMC pelo denominador."));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao("- Multiplicar o resultado pelo numerador."));
		operacao.getRetorno().add(LineSeparator.BREAK);

		List<Fracao> fracoesRetorno = transformarFracoesMesmoDenominador();
		
		operacao.getRetorno().add(new Descricao("Substituindo o denominador nas frações temos:"));
		exibirIgualdadeFracoes(fracoes, fracoesRetorno);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		fracoesRetorno = resolverDenominadores(fracoesRetorno);
		
		operacao.getRetorno().add(new Descricao("Dividindo o MMC pelo denominador temos:"));
		exibirIgualdadeFracoes(fracoes, fracoesRetorno);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		fracoesRetorno = resolverNumeradores(fracoesRetorno);		

		operacao.getRetorno().add(new Descricao("Multiplicando o resultado pelo numerador temos:"));
		exibirIgualdadeFracoes(fracoes, fracoesRetorno);
		operacao.getRetorno().add(LineSeparator.BREAK);
				
		resolverFracao(fracoesRetorno);
	}

	private List<Fracao> resolverDenominadores(List<Fracao> fracoes) {
		
		List<Fracao> fracoesRetorno = new ArrayList<>();
		
		for (int i = 0; i < fracoes.size(); i++) {		
			Fracao fracao = fracoes.get(i);
			fracoesRetorno.add(new Fracao(OperacaoUtil.divisao(fracao.getDenominador(), this.fracoes.get(i).getDenominador()).getQuociente(), fracao.getDenominador()));
		}
		
		return fracoesRetorno;
	}

	private List<Fracao> resolverNumeradores(List<Fracao> fracoes) {
		
		List<Fracao> fracoesRetorno = new ArrayList<>();
		
		for (int i = 0; i < fracoes.size(); i++) {		
			Fracao fracao = fracoes.get(i);
			fracoesRetorno.add(new Fracao(OperacaoUtil.multiplicacao(this.fracoes.get(i).getNumerador(), fracao.getNumerador()), fracao.getDenominador()));
		}
		
		return fracoesRetorno;
	}

	private void exibirIgualdadeFracoes(List<Fracao> fracoes1, List<Fracao> fracoes2) {
		Fracao fracao = fracoes1.get(0);
		Fracao fracao2 = fracoes2.get(0);
		
		abreMath();

		montaFracao(fracao, false);
		
		for (int i = 1; i < fracoes1.size(); i++) {
			fracao = fracoes1.get(i);
			montaFracao(fracao, true);
			
		}
		
		addIgualdade();
		
		montaFracao(fracao2, false);
		
		for (int i = 1; i < fracoes2.size(); i++) {
			fracao2 = fracoes2.get(i);
			montaFracao(fracao2, true);
			
		}
		
		fechaMath();
	}

	private void addIgualdade() {
		operacao.getRetorno().add(MathjaxTag.MO_OPEN);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
		
	}

	private List<Fracao> transformarFracoesMesmoDenominador() {
		
		List<Fracao> fracoesRetorno = new ArrayList<>();
		
		for (Fracao fracao : fracoes) {
			Fracao frac = new Fracao(fracao.getNumerador(), mmcBuild.getResultado());
			fracoesRetorno.add(frac);
		}		
		return fracoesRetorno;
	}

	private void resolverFracao(List<Fracao> fracoesMmc) throws BusinessException, RegraException {
		
		Fracao somaNominadores = new Fracao(new Inteiro(0), new Inteiro(0));

		for (int i = 0; i < fracoesMmc.size(); i++) {
			Fracao fracaoMmc = fracoesMmc.get(i);
			somaNominadores.setNumerador(OperacaoUtil.calculaSomaSubtracao(somaNominadores.getNumerador(), fracaoMmc.getNumerador()));
			somaNominadores.setDenominador(fracaoMmc.getDenominador());
		}		
		
		operacao.getRetorno().add(new Descricao("Resolvendo os nominadores temos:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(fracoesMmc.get(0).getNumerador(), false));
		
		for (int i = 1; i < fracoesMmc.size(); i++) {
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(fracoesMmc.get(i).getNumerador(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
		}
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(somaNominadores.getNumerador());
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao("Depois de somar teremos:"));
		abreMath();
		montaFracao(somaNominadores.getNumerador(), somaNominadores.getDenominador());
		fechaMath();
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		simplificarFracao(somaNominadores);
	}

	private void simplificarFracao(Fracao fracao) throws BusinessException, RegraException {
		fracaoSimplificacaoBuild = new FracaoSimplificacaoBuild(fracao, operacao);
		fracaoSimplificacaoBuild.resolver();
	}

	private void validarDenominadorIgual() throws RegraException, BusinessException {
		operacao.getRetorno().add(new Descricao("Validando se as frações possuem o mesmo denominador:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		if (OperacaoUtil.validarDenominadorIgual(fracoes)) {
			operacao.getRetorno().add(new Descricao("Frações possuem o mesmo denominador, então:"));
			resolverFracao(fracoes);
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
			throw new RegraException();
		}
		
		operacao.getRetorno().add(new Descricao("Frações não possuem o mesmo denominador, então:"));

		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void carregarMmcBuild() {
		List<Inteiro> denominadores = new ArrayList<>();
		for (Fracao fracao : fracoes) {
			denominadores.add(fracao.getDenominador());
		}
		MMC mmc = new MMC(denominadores, new Inteiro(1));
		mmcBuild = new MMCBuild(mmc, operacao);
	}

	@Override
	protected void validarParametros() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
