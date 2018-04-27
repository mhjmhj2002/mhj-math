package com.mhj.math.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.math.build.MDCBuild;
import com.mhj.math.data.Inteiro;
import com.mhj.math.dto.MdcDto;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.MDC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.validation.MDCValidation;

@Controller
@RequestMapping("/math/mdc")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class MDCController {

	@Autowired
	private MDCBuild mdcBuild;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new MDCValidation());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form(MdcDto mdc) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/mdc");//

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name="calcular_mdc")
	public ModelAndView calcular(@RequestParam("numeros") List<Integer> numeros, Locale locale) throws BusinessException {
		
		List<Inteiro> nums = new ArrayList<>();
		
		for (int i = 1; i < numeros.size(); i++) {
			nums.add(new Inteiro(numeros.get(i)));			
		}
		
		MDC mdc = new MDC(nums, new Inteiro(1));
		
		mdcBuild.setMdc(mdc);
		mdcBuild.setLocale(locale);
		mdcBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			mdcBuild.resolver();
		} catch (RegraException e) {
			e.printStackTrace();
		}
		
		Operacao operacao = mdcBuild.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/mdc_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
