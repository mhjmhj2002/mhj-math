package com.mhj.math.controllers;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.math.daos.UsuarioDAO;
import com.mhj.math.models.Role;
import com.mhj.math.models.Usuario;

@Controller
public class HomeController {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private MailSender sender;
	
	private Logger LOG = Logger.getLogger(this.getClass());

	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home");
		
		return modelAndView;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn")
	public String urlMagicaMaluca() {
		Usuario usuario = new Usuario(); 
		usuario.setNome("Admin");
		usuario.setEmail("admin@casadocodigo.com.br");
		usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		
		usuarioDao.gravar(usuario);
		
		return "Url Mágica executada";
	}
	
	@RequestMapping("/contato")
	public ModelAndView contato(String mensagem){
		ModelAndView modelAndView = new ModelAndView("contato");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView contatar(String mensagem){
		String retorno = "Email enviado, aguarde nosso retorno.";
		ModelAndView modelAndView = new ModelAndView("contato_response");
		try {
			enviaEmailContato(mensagem);
		} catch (Exception e) {
			retorno = "Ocorreu um problema ao enviar o email, tente mais tarde.";
			LOG.error("Erro envio email contato: ", e);		
		}
		
		modelAndView.addObject("retorno", retorno);
		
		return modelAndView;
	}

	private void enviaEmailContato(String mensagem) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Contato");
		email.setTo("contato@manuelhj.com.br");
		email.setText(mensagem);
		email.setFrom("manuelhj@manuelhj.com.br");
		sender.send(email);
	}
	
}








