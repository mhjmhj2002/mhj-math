package com.mhj.math.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/math/potencia")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Slf4j
public class PotenciaController {
	
	@Autowired
	private PotenciaSomaBuild potenciaSomaBuild;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new PotenciaValidation());
	}

	@GetMapping( name="soma", value="soma")
	public ModelAndView soma(PotenciaDto potencia) {
		return new ModelAndView("math/ef2/8ano/potencia_soma");
	}

	@PostMapping( name = "calcular_ss_potencia", value="calcular_ss_potencia")
	public ModelAndView calcularSoma(@RequestParam("bases") List<Integer> bases, @RequestParam("expoentes") List<Integer> expoentes, @RequestParam("sinaisExpoente") List<String> sinaisExpoente, @RequestParam("sinaisBase") List<String> sinaisBase, Locale locale)
			throws BusinessException {

		List<Inteiro> baseList = new ArrayList<>();
		List<Inteiro> expList = new ArrayList<>();
		List<Potenciacao> potencias = new ArrayList<>();

		for (int i = 1; i < expoentes.size(); i++) {
			expList.add(new Inteiro(Integer.valueOf(sinaisExpoente.get(i) + expoentes.get(i))));
		}
		
		for (int i = 1; i < bases.size(); i++) {
			baseList.add(new Inteiro(Integer.valueOf(sinaisBase.get(i) + bases.get(i))));
		}
		
		int pos = 0;
		for (Inteiro num : baseList) {
			Potenciacao potencia = new Potenciacao(num, expList.get(pos));
			potencias.add(potencia);
			pos++;
		}
		
		potenciaSomaBuild.setPotencias(potencias);
		potenciaSomaBuild.setLocale(locale);
		potenciaSomaBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			potenciaSomaBuild.resolver();
		} catch (RegraException e) {
			log.error("Erro de regra");
		}
		Operacao operacao = potenciaSomaBuild.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/8ano/potencia_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
