package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
public class FracaoBuildSimplificacao extends FracaoBuild {

	@Autowired
	private MessageSource messageSource;

	Locale locale;

	@Autowired
	MMCBuild mmcBuild;

	Fracao fracao;
	Divisao divisao;

	public FracaoBuildSimplificacao() {
		super();
	}

	public FracaoBuildSimplificacao(List<Fracao> fracoes, Operacao operacao) {
		super(fracoes, operacao);
	}

	public FracaoBuildSimplificacao(Fracao fracao, Operacao operacao) {
		super(null, operacao);
		this.fracao = fracao;
		divisao = null;
	}

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
		validarFracaoIrredutivel();
	}

	@Override
	protected void titulo() {
		operacao.getRetorno().add(new Descricao("Simplificando fração: "));
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

		operacao.getRetorno().add(new Descricao("Para simplificar uma fração, devemos:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(
				"- Calcular o MMC do numerador e denominador até se tornar irredutível, ou seja, quando o primeiro número dos dois não seja mais divisível."));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao("- Dividir o numerador e o denominador pelo MMC."));
		operacao.getRetorno().add(LineSeparator.BREAK);

		carregarMmc();
		try {
			mmcBuild.resolver();
		} catch (RegraException e) {
		}

		operacao.getRetorno().add(new Descricao("Dividindo o numerador e o denominador pelo MMC temos:"));
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
		operacao.getRetorno().add(new Descricao("O resultado é: "));
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
			operacao.getRetorno().add(new Descricao("Numerador maior que o denominador, então dividimos eles:"));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			abreMath();
			operacao.getRetorno().add(MathjaxTag.MO_OPEN);
			operacao.getRetorno().add(divisao.getQuociente());
			operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
			montaFracao(divisao.getResto(), fracao.getDenominador());
			fechaMath();
			fracao = new Fracao(divisao.getResto(), fracao.getDenominador());
			
//			throw new RegraException();
		}
	}

	private void validarFracaoIrredutivel() throws RegraException {
		if (OperacaoUtil.mdc(fracao.getNumerador(), fracao.getDenominador()).getValor() == 1) {
			operacao.getRetorno().add(new Descricao("Resultado"));
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
			operacao.getRetorno().add(new Descricao("Numerador igual que o denominador, então dividimos eles:"));
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
		if ( (fracao.getNumerador().getValor() > fracao.getDenominador().getValor()) && (OperacaoUtil.resto(fracao.getNumerador(), fracao.getDenominador()).getValor().equals(0)) ) {
			operacao.getRetorno().add(new Descricao("Numerador igual que o denominador, então dividimos eles:"));
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
