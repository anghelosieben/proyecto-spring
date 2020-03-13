package com.aplication.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.aplication.proyecto.auth.handler.LoginSuccessHandler;
import com.aplication.proyecto.service.UsuarioDetailService;



@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private UsuarioDetailService usuarioDetailS;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		//PasswordEncoder encoder=passwordEncoder();
		//UserBuilder users=User.builder().passwordEncoder(password->{return encoder.encode(password); });		
		/*builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMI","USER"))
			.withUser(users.username("anghelo").password("12345").roles("USER"));*/
		builder.userDetailsService(usuarioDetailS).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/","/assets/**").permitAll()
			.antMatchers("/prueba/**").hasAnyRole("ROLE_ADMI","ROLE_USER")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/persona/index")	
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error_403");		
		
	}
}
