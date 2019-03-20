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

import com.mhj.math.build.MMCBuild;
import com.mhj.math.data.Inteiro;
import com.mhj.math.dto.MmcDto;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.validation.MMCValidation;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/math/mmc")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Slf4j
public class MMCController {

	@Autowired
	private MMCBuild mmcBuild;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new MMCValidation());
	}

	@GetMapping
	public ModelAndView form(MmcDto mmc) {
		return new ModelAndView("math/ef2/6ano/mmc");
	}

	@PostMapping( name = "calcular_mmc")
	public ModelAndView calcular(@RequestParam("numeros") List<Integer> numeros, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();

		for (int i = 1; i < numeros.size(); i++) {
			nums.add(new Inteiro(numeros.get(i)));
		}

		MMC mmc = new MMC(nums, new Inteiro(1));

		mmcBuild.setMmc(mmc);
		mmcBuild.setLocale(locale);
		mmcBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			mmcBuild.resolver();
		} catch (RegraException e) {
			log.error("Erro de regra: ", e);
		}
		
		Operacao operacao = mmcBuild.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/mmc_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
