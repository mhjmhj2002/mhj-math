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

import com.mhj.math.build.PotenciaSomaBuild;
import com.mhj.math.data.Inteiro;
import com.mhj.math.dto.PotenciaDto;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.validation.PotenciaValidation;

@Controller
@RequestMapping("/math/potencia")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PotenciaController {
	
	@Autowired
	private PotenciaSomaBuild potenciaSomaBuild;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new PotenciaValidation());
	}

	@RequestMapping(method = RequestMethod.GET, name="soma", value="soma")
	public ModelAndView soma(PotenciaDto potencia) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/potencia_soma");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "calcular_ss_potencia", value="calcular_ss_potencia")
	public ModelAndView calcularSoma(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Potenciacao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Potenciacao potencia = new Potenciacao(num, denoms.get(pos));
			fracoes.add(potencia);
			pos++;
		}
		
		potenciaSomaBuild.setPotencias(fracoes);
		potenciaSomaBuild.setLocale(locale);
		potenciaSomaBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			potenciaSomaBuild.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = potenciaSomaBuild.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/potencia_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
