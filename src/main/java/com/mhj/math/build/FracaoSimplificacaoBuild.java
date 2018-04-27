package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.OperacaoUtil;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FracaoSimplificacaoBuild extends FracaoBuild {

	@Autowired
	MMCBuild mmcBuild;

	Fracao fracao;
	Divisao divisao;

	public FracaoSimplificacaoBuild() {
		super();
	}

	public FracaoSimplificacaoBuild(List<Fracao> fracoes, Operacao operacao, MessageSource messageSource) {
		super(fracoes, operacao, messageSource);
	}

	public FracaoSimplificacaoBuild(Fracao fracao, Operacao operacao, MessageSource messageSource) {
		super(null, operacao, messageSource);
		this.fracao = fracao;
		divisao = null;
	}

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
		validarFracaoIrredutivel();
	}

	@Override
	protected void titulo() {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.titulo.1", null, locale)));
		abreMath();
		montaFracao(fracao, false);
		fechaMath();
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	@Override
	protected void regras() throws BusinessException, RegraException {

		verificarNumeradorIgual();

		validarFracaoIrredutivel();

		verificarNumeradorMaiorSemResto();

		verificarNumeradorMaiorComResto();
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.1", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(
				messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.2", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.3", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		carregarMmc();
		try {
			mmcBuild.resolver();
		} catch (RegraException e) {
		}

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.4", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(fracao.getNumerador());
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(mmcBuild.getResultado());
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Letra.e);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(fracao.getDenominador());
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(mmcBuild.getResultado());
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.5", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		fracao = new Fracao(OperacaoUtil.divisao(fracao.getNumerador(), mmcBuild.getResultado()).getQuociente(),
				OperacaoUtil.divisao(fracao.getDenominador(), mmcBuild.getResultado()).getQuociente());

		abreMath();
		if (divisao != null) {
			operacao.getRetorno().add(MathjaxTag.MO_OPEN);
			operacao.getRetorno().add(divisao.getQuociente());
			operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
		}
		montaFracao(fracao.getNumerador(), fracao.getDenominador());
		fechaMath();

		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void verificarNumeradorMaiorComResto() throws RegraException {
		if (fracao.getNumerador().getValor() > fracao.getDenominador().getValor()) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorMaiorComResto.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			abreMath();
			operacao.getRetorno().add(MathjaxTag.MO_OPEN);
			operacao.getRetorno().add(divisao.getQuociente());
			operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
			montaFracao(divisao.getResto(), fracao.getDenominador());
			fechaMath();
			fracao = new Fracao(divisao.getResto(), fracao.getDenominador());

			// throw new RegraException();
		}
	}

	private void validarFracaoIrredutivel() throws RegraException {
		if (OperacaoUtil.mdc(fracao.getNumerador(), fracao.getDenominador()).getValor() == 1) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.validarFracaoIrredutivel.1", null, locale)));
			abreMath();
			if (divisao != null) {
				operacao.getRetorno().add(MathjaxTag.MO_OPEN);
				operacao.getRetorno().add(divisao.getQuociente());
				operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
			}
			montaFracao(fracao.getNumerador(), fracao.getDenominador());
			fechaMath();
			throw new RegraException();
		}
	}

	public void setFracao(Fracao fracao) {
		this.fracao = fracao;
	}

	private void carregarMmc() {
		mmcBuild.setLocale(locale);
		mmcBuild.setOperacao(this.getOperacao());
		List<Inteiro> denominadores = new ArrayList<>();
		denominadores.add(fracao.getNumerador());
		denominadores.add(fracao.getDenominador());
		MMC mmc = new MMC(denominadores, new Inteiro(1), true);
		mmcBuild.setMmc(mmc);
	}

	private void verificarNumeradorIgual() throws RegraException {
		if (fracao.getNumerador().getValor() == fracao.getDenominador().getValor()) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorIgual.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.DIVISAO);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(divisao.getQuociente());
			throw new RegraException();
		}
	}

	private void verificarNumeradorMaiorSemResto() throws RegraException {
		if ((fracao.getNumerador().getValor() > fracao.getDenominador().getValor())
				&& (OperacaoUtil.resto(fracao.getNumerador(), fracao.getDenominador()).getValor().equals(0))) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorMaiorSemResto.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.DIVISAO);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(divisao.getQuociente());
			throw new RegraException();
		}
	}

}
