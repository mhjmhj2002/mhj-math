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
import com.mhj.math.operacao.MDC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.MathProperties;
import com.mhj.math.util.MathjaxUtil;
import com.mhj.math.util.OperacaoUtil;

public class MDCBuild extends Build {
	private MDC mdc;
	List<MDC> decomposicoes;
	Inteiro resultado;

	public MDCBuild(MDC mdc, Operacao operacao) {
		super(operacao);
		this.mdc = mdc;
		decomposicoes = new ArrayList<>();
	}

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
		if (mdc == null) {
			throw new BusinessException("MDC obrigatório!");
		}
		if (mdc.getNumeros() == null || mdc.getNumeros().isEmpty()) {
			throw new BusinessException("MDC obrigatório!");
		}
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao("Resolvendo MDC de ( "));
		
		for (Inteiro numero : mdc.getNumeros()) {
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
		calcularDecomposicoes(mdc);
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
		operacao.getRetorno().add(new Descricao("MDC"));
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
		
		for (Inteiro numero : mdc.getNumeros()) {
			if(!OperacaoUtil.ehPrimo(numero)){
				primos = false;
				break;
			}
		}
		
		if (primos) {
			operacao.getRetorno().add(new Descricao("numeros são primos por tanto:"));
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao("MDC"));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(new Inteiro(1));
			operacao.getRetorno().add(LineSeparator.BREAK);
			throw new RegraException();
		}else{
			operacao.getRetorno().add(new Descricao("numeros não são primos."));
			operacao.getRetorno().add(LineSeparator.BREAK);
		}
	}

	private void calcularDecomposicoes(MDC mdc) throws BusinessException{
		
		decomposicoes.add(mdc);
		if(calculoDecomposicoesFinalizado(mdc)){
			return;
		}
		
		List<Inteiro> numeros = new ArrayList<>();
		
		for (Inteiro numero : mdc.getNumeros()) {
			if (numero.getValor() < 1) {
				throw new BusinessException("erro no calculo de decomposicao");
			}
			
			if (numero.getValor() == 1) {
				numeros.add(numero);
				continue;
			}
			
			if (numero.getValor() < mdc.getDivisor().getValor()) {
				numeros.add(numero);
				continue;
			}
			
			
			if (OperacaoUtil.resto(numero, mdc.getDivisor()).getValor() == 0) {
				Divisao divisao = OperacaoUtil.divisao(numero, mdc.getDivisor());	
				numeros.add(divisao.getQuociente());
				continue;
			}else{
				numeros.add(numero);
			}
		}

		MDC calculo;
		calculo = new MDC(numeros, mdc.getResultado());
		calcularDecomposicoes(calculo);
		
	}

	private boolean calculoDecomposicoesFinalizado(MDC mdc) {
		List<Inteiro> numeros = mdc.getNumeros();
		for (Inteiro inteiro : numeros) {
			if (inteiro.getValor() > 1) {
				return false;
			}
		}
		return true;
	}
	
	private List<Inteiro> montaMatriz() {
		List<Inteiro> resultados = new ArrayList<>(); 
		List<ValueComposta> values = new ArrayList<>();
		List<PropertyComposta> properties = new ArrayList<>();
		int qtdeNumeros = mdc.getNumeros().size();
		
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
		
		for (MDC mdc : decomposicoes) {
			operacao.getRetorno().add(MathjaxTag.MTR_OPEN);
			for (Inteiro numero : mdc.getNumeros()) {
				operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
				operacao.getRetorno().add(MathjaxTag.MN_OPEN);
				operacao.getRetorno().add(numero);
				operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
				operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			}
			operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(mdc.getDivisor());
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			if (mdc.isDivisivelPorTodos()) {
				resultados.add(mdc.getDivisor());
				operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
				operacao.getRetorno().add(MathjaxTag.MN_OPEN);
				operacao.getRetorno().add(new Descricao("Divisivel por todos"));
				operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
				operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			}
			resultado = mdc.getResultado();	
			operacao.getRetorno().add(MathjaxTag.MTR_CLOSE);
		}
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		return resultados;
	}

}
