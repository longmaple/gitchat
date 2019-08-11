package com.longmaple.ttmall.authentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
	}
	
	private JwtTokenStore tokenStore() {    
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {    
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter(); 
		KeyStoreKeyFactory keyStoreKeyFactory = 
				new KeyStoreKeyFactory(new ClassPathResource("mykeypair.jks"), "123456".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeypair"));
		return converter;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
		.passwordEncoder(passwordEncoder())
		.tokenKeyAccess("permitAll()");
		// .checkTokenAccess("isAuthenticated()");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}
	
}
