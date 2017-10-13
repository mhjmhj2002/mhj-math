package com.mhj.math.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

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
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class MmcController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new EquacaoGrau2Validation());
	}

	@RequestMapping("mmc")
	public ModelAndView form(EquacaoGrau2 equacaoGrau2) {
		ModelAndView modelAndView = new ModelAndView("math/mmc");
		modelAndView.addObject("sinais", Sinal.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView calcular(List<Integer> valores, Locale locale, BindingResult result, RedirectAttributes redirectAttributes) throws BusinessException, RegraException {

		ModelAndView modelAndView = new ModelAndView("math/equacao_resultado");
		modelAndView.addObject("linha", "teste");

		return modelAndView;
	}

}
