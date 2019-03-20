package com.mhj.math.data;

import com.mhj.math.data.interfaces.Info;

public class Descricao implements Info {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 320338538072567068L;
	String html;

	public Descricao(String descricao) {
		this.html = descricao;
	}

	public String getDescricao() {
		return html;
	}

	@Override
	public String getHtml() {
		return this.html;
	}

	@Override
	public String getTexto() {
		return this.html;
	}

}
