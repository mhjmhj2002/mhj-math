package com.mhj.math.controllers;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.mhj.math.build.EquacaoGrau2Build;
import com.mhj.math.builder.EquacaoGrau2Builder;
import com.mhj.math.config.UnitTestContext;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.operacao.EquacaoGrau2;
import com.mhj.math.operacao.Operacao;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { UnitTestContext.class })
public class EquacaoControllerTest {

	private RedirectAttributesModelMap redirectAttributes;
	MockHttpServletRequest mockRequest;
	private EquacaoController equacaoController;
	private EquacaoGrau2Build equacaoGrau2Build;

	//@Before
	public void setUp() {
		redirectAttributes = new RedirectAttributesModelMap();
		mockRequest = new MockHttpServletRequest("POST", "/math/equacao");

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);

		equacaoController = new EquacaoController();
		equacaoGrau2Build = new EquacaoGrau2Build();
		equacaoGrau2Build.setLocale(Locale.getDefault());
		equacaoGrau2Build.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));
		equacaoGrau2Build.addMessageSource(source);

		ReflectionTestUtils.setField(equacaoController, "equacaoGrau2Build", equacaoGrau2Build);
	}

	//@Test
	public void calcularOk() {
		EquacaoGrau2 equacaoGrau2 = EquacaoGrau2Builder.create().getEquacaoOk();
		BindingResult result = bindAndValidate(mockRequest, equacaoGrau2);

		try {
			equacaoController.calcular(equacaoGrau2, Locale.getDefault(), result, redirectAttributes);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private BindingResult bindAndValidate(HttpServletRequest request, Object formObject) {
		WebDataBinder binder = new WebDataBinder(formObject);
//        binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
//        binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
		return binder.getBindingResult();
	}
}
