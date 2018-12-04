package com.mhj.math.infra;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MailSend {

	@Autowired
	private MailSender sender;

	@Async
	public void send(SimpleMailMessage email) {

		log.info("antes send: " + new Date());
		sender.send(email);
		log.info("depois send: " + new Date());

	}

}
