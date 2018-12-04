package com.mhj.math.config;

import org.springframework.context.annotation.ComponentScan;

import com.mhj.math.builder.EquacaoGrau2Builder;

@ComponentScan(basePackageClasses={EquacaoGrau2Builder.class})
public class UnitTestContext {

//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
	
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}
}
