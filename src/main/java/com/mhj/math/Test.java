package com.mhj.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.mhj.math.build.EquacaoGrau2Build;
import com.mhj.math.build.FracaoBuild;
import com.mhj.math.build.FracaoBuildDivisao;
import com.mhj.math.build.FracaoBuildMultiplicacao;
import com.mhj.math.build.FracaoBuildSoma;
import com.mhj.math.build.MDCBuild;
import com.mhj.math.build.MMCBuild;
import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.FiltroOperacoes;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.EquacaoGrau2;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.MDC;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;

public class Test {

	public static void main(String[] args) {

		try {
//			BigDecimal TesteBigDecimal = new BigDecimal("12345678901234567890123456789012345678901234567890");
			BigDecimal TesteBigDecimal = new BigDecimal("99999999999999999999999999999999999999999999999999");
			System.out.println(TesteBigDecimal);
//			BigInteger TesteBigInteger = new BigInteger("12345678901234567890123456789012345678901234567890");
			BigInteger TesteBigInteger = new BigInteger("99999999999999999999999999999999999999999999999999");
			System.out.println(TesteBigInteger);
//			Long TesteLong = new Long("123456789012345678");
			Long TesteLong = new Long("999999999999999999");
			System.out.println(TesteLong);
//			Integer TesteInt= new Integer("123456789");
			Integer TesteInt= new Integer("999999999");
			System.out.println(TesteInt);
			//testarEquacao2Grau();
			// testarMMC2Numeros();
			// testarMMCVariosNumeros();
			// testarSomafracoes();
			// testarMDCVariosNumeros();
			// teste();
			// testarMultiplicacaoFracoes();
			// testarDivisaoFracoes();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testarMultiplicacaoFracoes() throws BusinessException {
		List<Fracao> fracoes = new ArrayList<>();

		// fracoes.add(new Fracao(new Inteiro(1), new Inteiro(2)));
		// fracoes.add(new Fracao(new Inteiro(1), new Inteiro(4)));

		// fracoes.add(new Fracao(new Inteiro(10), new Inteiro(4)));
		// fracoes.add(new Fracao(new Inteiro(12), new Inteiro(5)));
		// fracoes.add(new Fracao(new Inteiro(-3), new Inteiro(6)));

		fracoes.add(new Fracao(new Inteiro(3), new Inteiro(3)));
		fracoes.add(new Fracao(new Inteiro(1), new Inteiro(2)));

		List<FiltroOperacoes> filtroOperacoes = new ArrayList<>();
		filtroOperacoes.add(FiltroOperacoes.FRACAO);

		Operacao operacao = new Operacao(new ArrayList<>(), filtroOperacoes);

		FracaoBuild fracaoBuild = new FracaoBuildMultiplicacao(fracoes, operacao);
		try {
			fracaoBuild.resolver();
		} catch (RegraException e) {
			e.printStackTrace();
		}
		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "teste4", operacao.getRetorno());
		impressao.imprimirTexto("c:/manuel/", "teste4", operacao.getRetorno());

	}

	public static void testarDivisaoFracoes() throws BusinessException {
		List<Fracao> fracoes = new ArrayList<>();

		// fracoes.add(new Fracao(new Inteiro(9), new Inteiro(2)));
		// fracoes.add(new Fracao(new Inteiro(7), new Inteiro(3)));

		fracoes.add(new Fracao(new Inteiro(12), new Inteiro(5)));
		fracoes.add(new Fracao(new Inteiro(6), new Inteiro(7)));
		fracoes.add(new Fracao(new Inteiro(7), new Inteiro(3)));

		List<FiltroOperacoes> filtroOperacoes = new ArrayList<>();
		filtroOperacoes.add(FiltroOperacoes.FRACAO);

		Operacao operacao = new Operacao(new ArrayList<>(), filtroOperacoes);

		FracaoBuildDivisao fracaoBuild = new FracaoBuildDivisao(fracoes, operacao);
		try {
			fracaoBuild.resolver();
		} catch (RegraException e) {
			e.printStackTrace();
		}
		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "teste4", operacao.getRetorno());
		impressao.imprimirTexto("c:/manuel/", "teste4", operacao.getRetorno());

	}

	// private static void teste() {
	// Teste matrix = new Teste(new ArrayList<>());
	// matrix.getColuna(index);
	// }

	public static void testarSomafracoes() throws BusinessException {

		List<Fracao> fracoes = new ArrayList<>();

		// fracoes.add(new Fracao(new Inteiro(1), new Inteiro(2)));
		// fracoes.add(new Fracao(new Inteiro(1), new Inteiro(4)));

		// fracoes.add(new Fracao(new Inteiro(10), new Inteiro(4)));
		// fracoes.add(new Fracao(new Inteiro(12), new Inteiro(5)));
		// fracoes.add(new Fracao(new Inteiro(-3), new Inteiro(6)));

		fracoes.add(new Fracao(new Inteiro(1), new Inteiro(2)));
		fracoes.add(new Fracao(new Inteiro(-1), new Inteiro(4)));

		List<FiltroOperacoes> filtroOperacoes = new ArrayList<>();
		filtroOperacoes.add(FiltroOperacoes.FRACAO);

		Operacao operacao = new Operacao(new ArrayList<>(), filtroOperacoes);

		FracaoBuild fracaoBuild = new FracaoBuildSoma(fracoes, operacao);
		try {
			fracaoBuild.resolver();
		} catch (RegraException e) {
			e.printStackTrace();
		}
		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "teste4", operacao.getRetorno());
		impressao.imprimirTexto("c:/manuel/", "teste4", operacao.getRetorno());
	}

	public static void testarMMCVariosNumeros() throws BusinessException {
		List<Inteiro> numeros = new ArrayList<>();
		numeros.add(new Inteiro(10));
		numeros.add(new Inteiro(11));
		// numeros.add(new Inteiro(21));
		// numeros.add(new Inteiro(20));
		// numeros.add(new Inteiro(25));
		MMC mmc = new MMC(numeros, new Inteiro(1));

		List<FiltroOperacoes> filtroOperacoes = new ArrayList<>();
		filtroOperacoes.add(FiltroOperacoes.FRACAO);

		Operacao operacao = new Operacao(new ArrayList<>(), filtroOperacoes);

		MMCBuild mmcBuild = new MMCBuild(mmc, operacao);

		try {
			mmcBuild.resolver();
		} catch (RegraException e) {
			e.printStackTrace();
		}

		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "teste3", operacao.getRetorno());
		impressao.imprimirTexto("c:/manuel/", "teste3", operacao.getRetorno());
	}

	public static void testarEquacao2Grau() throws BusinessException, RegraException {

		// 7x² + 3x + 4 = 0 D=-103 x1= < 0 x2= < 0
		Descricao a1 = new Descricao("7");
		Descricao b1 = new Descricao("3");
		Descricao c1 = new Descricao("4");
		Descricao resultado1 = new Descricao("0");
		Sinal sinalA1 = Sinal.getByHtml("+");
		Sinal sinalB1 = Sinal.getByHtml("+");
		Sinal sinalC1 = Sinal.getByHtml("+");
		Sinal sinalResultado1 = Sinal.getByHtml("+");

		// 1x² – 5x + 6 = 0 D=1 x1=3 x2=2
		Descricao a2 = new Descricao("1");
		Descricao b2 = new Descricao("5");
		Descricao c2 = new Descricao("6");
		Descricao resultado2 = new Descricao("0");
		Sinal sinalA2 = Sinal.getByHtml("+");
		Sinal sinalB2 = Sinal.getByHtml("-");
		Sinal sinalC2 = Sinal.getByHtml("+");
		Sinal sinalResultado2 = Sinal.getByHtml("+");

		// 1x² + 4x - -5 = 0 D=36 x1=1 x2=-5
		Descricao a3 = new Descricao("1");
		Descricao b3 = new Descricao("4");
		Descricao c3 = new Descricao("5");
		Descricao resultado3 = new Descricao("0");
		Sinal sinalA3 = Sinal.getByHtml("+");
		Sinal sinalB3 = Sinal.getByHtml("+");
		Sinal sinalC3 = Sinal.getByHtml("-");
		Sinal sinalResultado3 = Sinal.getByHtml("+");

		// 4x^2 + 2x - 6 = 0 D=100 x1=1 x2=-3/2
		Descricao a4 = new Descricao("4");
		Descricao b4 = new Descricao("2");
		Descricao c4 = new Descricao("6");
		Descricao resultado4 = new Descricao("0");
		Sinal sinalA4 = Sinal.getByHtml("+");
		Sinal sinalB4 = Sinal.getByHtml("+");
		Sinal sinalC4 = Sinal.getByHtml("-");
		Sinal sinalResultado4 = Sinal.getByHtml("+");

		// 4x² - 4x + 1 = 0 D=0 x1=1/2
		Descricao a5 = new Descricao("4");
		Descricao b5 = new Descricao("4");
		Descricao c5 = new Descricao("1");
		Descricao resultado5 = new Descricao("0");
		Sinal sinalA5 = Sinal.getByHtml("+");
		Sinal sinalB5 = Sinal.getByHtml("-");
		Sinal sinalC5 = Sinal.getByHtml("+");
		Sinal sinalResultado5 = Sinal.getByHtml("+");

		Operacao operacao = new Operacao(new ArrayList<>(), new ArrayList<>());

		EquacaoGrau2 equacaoGrau21 = new EquacaoGrau2(Letra.X, a1, b1, c1, sinalA1, sinalB1, sinalC1, resultado1,
				sinalResultado1);
		EquacaoGrau2Build build1 = new EquacaoGrau2Build(equacaoGrau21, operacao);

		EquacaoGrau2 equacaoGrau22 = new EquacaoGrau2(Letra.X, a2, b2, c2, sinalA2, sinalB2, sinalC2, resultado2,
				sinalResultado2);
		EquacaoGrau2Build build2 = new EquacaoGrau2Build(equacaoGrau22, operacao);

		EquacaoGrau2 equacaoGrau23 = new EquacaoGrau2(Letra.X, a3, b3, c3, sinalA3, sinalB3, sinalC3, resultado3,
				sinalResultado3);
		EquacaoGrau2Build build3 = new EquacaoGrau2Build(equacaoGrau23, operacao);

		EquacaoGrau2 equacaoGrau24 = new EquacaoGrau2(Letra.X, a4, b4, c4, sinalA4, sinalB4, sinalC4, resultado4,
				sinalResultado4);
		EquacaoGrau2Build build4 = new EquacaoGrau2Build(equacaoGrau24, operacao);

		EquacaoGrau2 equacaoGrau25 = new EquacaoGrau2(Letra.X, a5, b5, c5, sinalA5, sinalB5, sinalC5, resultado5,
				sinalResultado5);
		EquacaoGrau2Build build5 = new EquacaoGrau2Build(equacaoGrau25, operacao);

		Operacao retorno1 = build1.resolver();
		Operacao retorno2 = build2.resolver();
		Operacao retorno3 = build3.resolver();
		Operacao retorno4 = build4.resolver();
		Operacao retorno5 = build5.resolver();

		List<Data> retorno = new ArrayList<>();
		retorno.addAll(retorno1.getRetorno());
		retorno.addAll(retorno2.getRetorno());
		retorno.addAll(retorno3.getRetorno());
		retorno.addAll(retorno4.getRetorno());
		retorno.addAll(retorno5.getRetorno());

		// retorno.add(Simbolo.MENOR);
		// retorno.add(new Descricao("MATH"));
		// retorno.add(TagMathjaxPropertie.XMLNS);
		// retorno.add(TagMathjaxPropertie.DISPLAY);
		// retorno.add(Simbolo.MAIOR);
		//
		// retorno.add(TagMathjax.MFRAC_OPEN);
		//
		// retorno.add(TagMathjax.MROW_OPEN);
		// retorno.add(TagMathjax.MN_OPEN);
		// retorno.add(new Inteiro(2));
		// retorno.add(TagMathjax.MN_CLOSE);
		// retorno.add(TagMathjax.MI_OPEN);
		// retorno.add(Letra.a);
		// retorno.add(TagMathjax.MI_CLOSE);
		// retorno.add(TagMathjax.MROW_CLOSE);
		//
		// retorno.add(TagMathjax.MROW_OPEN);
		// retorno.add(TagMathjax.MN_OPEN);
		// retorno.add(new Inteiro(2));
		// retorno.add(TagMathjax.MN_CLOSE);
		// retorno.add(TagMathjax.MI_OPEN);
		// retorno.add(Letra.a);
		// retorno.add(TagMathjax.MI_CLOSE);
		// retorno.add(TagMathjax.MROW_CLOSE);
		//
		// retorno.add(TagMathjax.MFRAC_CLOSE);
		//
		// retorno.add(TagMathjax.MATH_CLOSE);

		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "teste", retorno);
		impressao.imprimirTexto("c:/manuel/", "teste", retorno);
		// System.out.println(retorno.toDescricao());

	}

	public static void testarMDCVariosNumeros() throws BusinessException, RegraException {
		List<Inteiro> numeros = new ArrayList<>();

		numeros.add(new Inteiro(180));
		numeros.add(new Inteiro(240));
		numeros.add(new Inteiro(270));

		// numeros.add(new Inteiro(20));
		// numeros.add(new Inteiro(25));

		// numeros.add(new Inteiro(3));
		// numeros.add(new Inteiro(4));

		MDC mmc = new MDC(numeros, new Inteiro(1));

		List<FiltroOperacoes> filtroOperacoes = new ArrayList<>();
		filtroOperacoes.add(FiltroOperacoes.FRACAO);

		Operacao operacao = new Operacao(new ArrayList<>(), filtroOperacoes);

		MDCBuild mmcBuild = new MDCBuild(mmc, operacao);

		mmcBuild.resolver();

		Impressao impressao = new Impressao();
		impressao.imprimirHTML("c:/manuel/", "testeMDC", operacao.getRetorno());
		impressao.imprimirTexto("c:/manuel/", "testeMDC", operacao.getRetorno());
	}

}
//naturais=0;1...
//inteiros=...-1;0;1...
//racionais..-1,5;-1;0;1/2;1...
//irracionais..-1,77...;0;1,77...;2...