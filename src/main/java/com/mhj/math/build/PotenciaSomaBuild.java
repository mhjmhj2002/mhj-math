package com.mhj.math.build;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Potenciacao;

@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PotenciaSomaBuild extends PotenciaBuild {

	@Override
	protected void validarParametros() throws BusinessException, RegraException {

	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("PotenciaSomaBuild.titulo.1", null, locale)));

		exibirPotencias(potencias);

		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		validarPotenciaNegativa();
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void exibirPotencias(List<Potenciacao> potencias) {
		Potenciacao potencia = potencias.get(0);

		abreMath();

		if (potencia.getBase().getValor() < 0) {
			montaPotencia(potencia, true);
		} else {
			montaPotencia(potencia, false);
		}

		for (int i = 1; i < potencias.size(); i++) {
			potencia = potencias.get(i);
			montaPotencia(potencia, true);

		}

		fechaMath();

	}

}
