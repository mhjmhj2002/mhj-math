package com.mhj.math.conf;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.mhj.math.daos.UsuarioDAO;

//@EnableWebMvcSecurity
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/ {

//	@Autowired
//	private UsuarioDAO usuarioDao;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/resources/**").permitAll()
//		.antMatchers("/math/**").permitAll()
//		.and().formLogin().loginPage("/login").permitAll()
////		.antMatchers("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn").permitAll()
////			.anyRequest().authenticated()
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.and().csrf().disable();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(usuarioDao)
//			.passwordEncoder(new BCryptPasswordEncoder());
//	}
	
}













