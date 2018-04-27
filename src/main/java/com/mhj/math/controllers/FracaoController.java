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

import com.mhj.math.build.FracaoDivisaoBuild;
import com.mhj.math.build.FracaoMultiplicacaoBuild;
import com.mhj.math.build.FracaoSimplificacaoBuild;
import com.mhj.math.build.FracaoSomaBuild;
import com.mhj.math.data.Inteiro;
import com.mhj.math.dto.FracaoDto;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.validation.FracaoValidation;

@Controller
@RequestMapping("/math/fracao")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FracaoController {
	
	@Autowired
	private FracaoSomaBuild fracaoBuildSoma;

	@Autowired
	private FracaoMultiplicacaoBuild fracaoBuildMultiplicacao;

	@Autowired
	private FracaoDivisaoBuild fracaoBuildDivisao;
	
	@Autowired
	private FracaoSimplificacaoBuild fracaoBuildSimplificacao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FracaoValidation());
	}

	@RequestMapping(method = RequestMethod.GET, name="soma", value="soma")
	public ModelAndView soma(FracaoDto fracao) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_soma");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, name="multiplicacao", value="multiplicacao")
	public ModelAndView multiplicacao(FracaoDto fracao) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_multiplicacao");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, name="divisao", value="divisao")
	public ModelAndView divisao(FracaoDto fracao) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_divisao");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, name="simplificacao", value="simplificacao")
	public ModelAndView simplificacao(FracaoDto fracao) {
		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_simplificacao");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "calcular_ss_fracao", value="calcular_ss_fracao")
	public ModelAndView calcularSoma(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildSoma.setFracoes(fracoes);
		fracaoBuildSoma.setLocale(locale);

		try {
			fracaoBuildSoma.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildSoma.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "calcular_mult_fracao", value="calcular_mult_fracao")
	public ModelAndView calcularMultiplicacao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildMultiplicacao.setFracoes(fracoes);
		fracaoBuildMultiplicacao.setLocale(locale);

		try {
			fracaoBuildMultiplicacao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildMultiplicacao.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "calcular_div_fracao", value="calcular_div_fracao")
	public ModelAndView calcularDivisao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildDivisao.setFracoes(fracoes);
		fracaoBuildDivisao.setLocale(locale);

		try {
			fracaoBuildDivisao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildDivisao.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "calcular_simpl_fracao", value="calcular_simpl_fracao")
	public ModelAndView calcularSimplificacao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildSimplificacao.setFracao(fracoes.get(0));
		fracaoBuildSimplificacao.setLocale(locale);

		try {
			fracaoBuildSimplificacao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildSimplificacao.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/6ano/fracao_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return modelAndView;
	}

}
