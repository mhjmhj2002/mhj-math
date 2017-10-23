package com.mhj.math.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.math.models.Contato;

@Repository
@Transactional
public class ContatoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Contato contato) {
		manager.persist(contato);
	}
	
	public void atualizar(Contato contato) {
		manager.merge(contato);
	}

}
