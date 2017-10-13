package com.mhj.math.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mhj.math.operacao.EquacaoGrau2;

public class EquacaoGrau2Validation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EquacaoGrau2.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "a", "EquacaoGrau2Build.field.required");
		ValidationUtils.rejectIfEmpty(errors, "b", "EquacaoGrau2Build.field.required");
		ValidationUtils.rejectIfEmpty(errors, "c", "EquacaoGrau2Build.field.required");
		ValidationUtils.rejectIfEmpty(errors, "sinalA", "EquacaoGrau2Build.field.required", "erro sinalA");
		ValidationUtils.rejectIfEmpty(errors, "sinalB", "EquacaoGrau2Build.field.required");
		ValidationUtils.rejectIfEmpty(errors, "sinalC", "EquacaoGrau2Build.field.required");
		
//		EquacaoGrau2 equacaoGrau2 = (EquacaoGrau2) target;
//		if (equacaoGrau2.getSinalA() == null) {
//			errors.rejectValue("sinalA", "field.required");
//		}
	}
	
}




