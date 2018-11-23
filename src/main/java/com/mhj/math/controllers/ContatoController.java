package com.mhj.math.controllers;

import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mhj.math.infra.MailSend;
import com.mhj.math.models.Contato;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	private Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private MailSend sender;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView contato(Contato contato) {
		ModelAndView modelAndView = new ModelAndView("contato");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView contatar(@Valid Contato contato, Locale locale, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return contato(contato);
			}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/contato/retornoContato");
		
		try {
			enviaEmailContato(contato);
		} catch (Exception e) {
			LOG.error("Erro envio email contato: ", e);
			e.printStackTrace();
		}
		
		return modelAndView;
	}

	private void enviaEmailContato(Contato contato) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Contato");
		email.setTo("mhjmhj2002@gmail.com");
		email.setBcc(contato.getEmail());
		email.setText(contato.getMensagem());
		email.setFrom("mhjapps@gmail.com");
		System.out.println("antes enviaEmailContato: " + new Date());
		LOG.info("antes enviaEmailContato: " + new Date());
		sender.send(email);
		System.out.println("depois enviaEmailContato: " + new Date());
		LOG.info("depois enviaEmailContato: " + new Date());
	}

}
