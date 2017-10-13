package com.mhj.math.data;

import java.util.List;

import com.mhj.math.data.interfaces.Expressao;
import com.mhj.math.data.interfaces.Property;

public class PropertyComposta {
	
	private Property property;

	private List<ValueComposta> values;

	public PropertyComposta(Property property, List<ValueComposta> values) {
		super();
		this.property = property;
		this.values = values;
	}

	public Expressao getProperty() {
		return property;
	}

	public ValueComposta getValue(int index) {
		return values.get(index);
	}

	public void setValue(ValueComposta value) {
		this.values.add(value);
	}

	public List<ValueComposta> getValues() {
		return values;
	}

}
