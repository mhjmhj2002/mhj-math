package com.mhj.math.controllers;

import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mhj.math.build.EquacaoGrau2Build;
import com.mhj.math.data.Descricao;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.metodo.Bhaskara;
import com.mhj.math.operacao.EquacaoGrau2;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.validation.EquacaoGrau2Validation;

@Controller
@RequestMapping("/math")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class EquacaoController {

	@Autowired
	private EquacaoGrau2Build equacaoGrau2Build;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new EquacaoGrau2Validation());
	}

	@RequestMapping("equacao")
	public ModelAndView form(EquacaoGrau2 equacaoGrau2) {
		ModelAndView modelAndView = new ModelAndView("math/em/1ano/equacao2grau");
		modelAndView.addObject("sinais", Sinal.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView calcular(@Valid EquacaoGrau2 equacaoGrau2, Locale locale, BindingResult result, RedirectAttributes redirectAttributes) throws BusinessException {
		
		if (result.hasErrors()) {
		return form(equacaoGrau2);
		}

		equacaoGrau2.setVariavel(Letra.X);
		equacaoGrau2.setResultado(new Descricao("0"));
		equacaoGrau2.setSinalResultado(Sinal.POSITIVO);
		equacaoGrau2.setMetodo(new Bhaskara());

		equacaoGrau2Build.setEquacaoGrau2(equacaoGrau2);
		equacaoGrau2Build.setLocale(locale);
		equacaoGrau2Build.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			equacaoGrau2Build.resolver();
		} catch (RegraException e) {
		}
		
		Operacao operacao = equacaoGrau2Build.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/em/1ano/equacao_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
