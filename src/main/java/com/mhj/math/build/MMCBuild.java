package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.data.PropertyComposta;
import com.mhj.math.data.TagComposta;
import com.mhj.math.data.ValueComposta;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.MathjaxValue;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.MathProperties;
import com.mhj.math.util.MathjaxUtil;
import com.mhj.math.util.OperacaoUtil;

public class MMCBuild extends Build{
	private MMC mmc;
	List<MMC> decomposicoes;
	Inteiro resultado;
	
	public MMCBuild(MMC mmc, Operacao operacao) {
		super(operacao);
		this.mmc = mmc;
		decomposicoes = new ArrayList<>();
	}

	@Override
	protected void validarParametros() throws BusinessException {
		if (mmc == null) {
			throw new BusinessException("MMC obrigatório!");
		}
		if (mmc.getNumeros() == null || mmc.getNumeros().isEmpty()) {
			throw new BusinessException("MMC obrigatório!");
		}
	}

	@Override
	protected void titulo() throws BusinessException {
		if (mmc.isIrredutivel()) {
			operacao.getRetorno().add(new Descricao(MathProperties.getPropertyString("MMCBuild.titulo.1")));
		}else{
			operacao.getRetorno().add(new Descricao(MathProperties.getPropertyString("MMCBuild.titulo.2")));
		}
		
		for (Inteiro numero : mmc.getNumeros()) {
			operacao.getRetorno().add(numero);
			operacao.getRetorno().add(Simbolo.VIRGULA);
			operacao.getRetorno().add(Simbolo.ESPACO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		verificarPrimos();
		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		calcularDecomposicoes(mmc);
		operacao.getRetorno().add(new Descricao("Resolvendo:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		abreMath();
		
		List<Inteiro> resultados = montaMatriz();
		
		fechaMath();
		
		operacao.getRetorno().add(new Descricao("Multiplicando temos:"));
		operacao.getRetorno().add(LineSeparator.BREAK);
		for (Inteiro inteiro : resultados) {
			operacao.getRetorno().add(inteiro);
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
		}
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(resultado);
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao("MMC"));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(resultado);
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
	}
	
	private void verificarPrimos() throws RegraException {
		operacao.getRetorno().add(new Descricao(MathProperties.getPropertyString("MMCBuild.verificarPrimos.1")));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		boolean primos = true;
		
		for (Inteiro numero : mmc.getNumeros()) {
			if(!OperacaoUtil.ehPrimo(numero)){
				primos = false;
				break;
			}
		}
		
		if (primos) {
			operacao.getRetorno().add(new Descricao("numeros são primos por tanto:"));
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao("MMC"));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			
			Inteiro multiplicacao = new Inteiro(1);
			
			for (Inteiro numero : mmc.getNumeros()) {
				operacao.getRetorno().add(numero);
				operacao.getRetorno().add(Simbolo.ESPACO);
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(Simbolo.ESPACO);
				multiplicacao = OperacaoUtil.multiplicacao(multiplicacao, numero);
			}
			
			operacao.getRetorno().remove(operacao.getRetorno().size()-1);
			operacao.getRetorno().remove(operacao.getRetorno().size()-1);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(multiplicacao);
			throw new RegraException();
		}else{
			operacao.getRetorno().add(new Descricao("numeros não são primos."));
		}
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void calcularDecomposicoes(MMC mmc) throws BusinessException{
		
		if (mmc.isIrredutivel()) {
			if(calculoDecomposicoesFinalizado(mmc)){
				return;
			}
			decomposicoes.add(mmc);
		}else{
			decomposicoes.add(mmc);
			if(calculoDecomposicoesFinalizado(mmc)){
				return;
			}
		}
		
		List<Inteiro> numeros = new ArrayList<>();
		
		for (Inteiro numero : mmc.getNumeros()) {
			if (numero.getValor() < 1) {
				throw new BusinessException("erro no calculo de decomposicao");
			}
			
			if (numero.getValor() == 1) {
				numeros.add(numero);
				continue;
			}
			
			if (numero.getValor() < mmc.getDivisor().getValor()) {
				numeros.add(numero);
				continue;
			}
			
			
			if (OperacaoUtil.resto(numero, mmc.getDivisor()).getValor() == 0) {
				Divisao divisao = OperacaoUtil.divisao(numero, mmc.getDivisor());	
				numeros.add(divisao.getQuociente());
				continue;
			}else{
				numeros.add(numero);
			}
		}

		MMC calculo;
		if (mmc.isIrredutivel()) {
			calculo = new MMC(numeros, mmc.getResultado(), true);
		}else{
			calculo = new MMC(numeros, mmc.getResultado());
		}
		calcularDecomposicoes(calculo);
		
	}

	private boolean calculoDecomposicoesFinalizado(MMC mmc) {
		List<Inteiro> numeros = mmc.getNumeros();
		for (Inteiro inteiro : numeros) {
			if (mmc.isIrredutivel()) {
				if (inteiro.getValor() < 2){
					return true;
				}else{
					return false;
				}
			}else 
				if (inteiro.getValor() > 1) {
				return false;
			}
		}
		return true;
	}

	public Inteiro getResultado() {
		return resultado;
	}
	
	private List<Inteiro> montaMatriz() {
		List<Inteiro> resultados = new ArrayList<>(); 
		List<ValueComposta> values = new ArrayList<>();
		List<PropertyComposta> properties = new ArrayList<>();
		int qtdeNumeros = mmc.getNumeros().size();
		
		ValueComposta value = new ValueComposta(MathjaxValue.PT, new Inteiro(qtdeNumeros));
		values = new ArrayList<>();
		values.add(value);
		PropertyComposta property = new PropertyComposta(MathjaxProperty.ROW_SPACING, values);		
		properties.add(property);
		
		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.EM, new Inteiro(1));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_SPACING, values);		
		properties.add(property);
				
		TagComposta tag = new TagComposta(MathjaxTag.MTABLE_OPEN, properties);		
		operacao.getRetorno().addAll(MathjaxUtil.montarTagComposta(tag));
		
		properties = new ArrayList<>();
		
		operacao.getRetorno().add(MathjaxTag.MTD_OPEN);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.CENTER); 
		for (int i = 0; i < qtdeNumeros; i++) {
			values.add(value);
		}
		values.add(value);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_ALIGN, values);		
		properties.add(property);
		
		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.PT, new Inteiro(qtdeNumeros));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.ROW_SPACING, values);		
		properties.add(property);
		
		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.EM, new Inteiro(1));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_SPACING, values);		
		properties.add(property);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.SOLID); 		
		values.add(value);
		value = new ValueComposta(MathjaxValue.NONE);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.ROW_LINES, values);		
		properties.add(property);

		values = new ArrayList<>();
		
		for (int i = 0; i < qtdeNumeros; i++) {
			if (i == qtdeNumeros-1) {
				value = new ValueComposta(MathjaxValue.SOLID);
				values.add(value);	
			}else{
				value = new ValueComposta(MathjaxValue.NONE);
				values.add(value);
			}
		}
		value = new ValueComposta(MathjaxValue.NONE);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_LINES, values);		
		properties.add(property);
		
		tag = new TagComposta(MathjaxTag.MTABLE_OPEN, properties);		
		operacao.getRetorno().addAll(MathjaxUtil.montarTagComposta(tag));
		
		for (MMC mmc : decomposicoes) {
			operacao.getRetorno().add(MathjaxTag.MTR_OPEN);
			for (Inteiro numero : mmc.getNumeros()) {
				operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
				operacao.getRetorno().add(MathjaxTag.MN_OPEN);
				operacao.getRetorno().add(numero);
				operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
				operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			}
			operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(mmc.getDivisor());
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			
			resultados.add(mmc.getDivisor());
			resultado = mmc.getResultado();	
			
			operacao.getRetorno().add(MathjaxTag.MTR_CLOSE);

		}
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		return resultados;
	}

}
