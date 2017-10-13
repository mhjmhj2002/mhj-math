package com.mhj.math.data;

import com.mhj.math.data.interfaces.Data;
import com.mhj.math.data.interfaces.Value;

public class ValueComposta {
	
	private Value value;
	private Data data;
	
	public ValueComposta(Value value) {
		this.value = value;
		this.data = new Descricao("");
	}
	
	public ValueComposta(Value value, Data data) {
		this.value = value;
		this.data = data;
	}
	
	public Data getData(){
		return new Descricao(data.getHtml() + this.value.getHtml());
	}

}
