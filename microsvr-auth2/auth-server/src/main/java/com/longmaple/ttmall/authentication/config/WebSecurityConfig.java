package com.longmaple.ttmall.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers().frameOptions().disable()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
		.and()
		.authorizeRequests().anyRequest().authenticated();
	}

}
