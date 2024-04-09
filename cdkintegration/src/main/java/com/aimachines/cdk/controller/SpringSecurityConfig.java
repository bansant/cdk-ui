package com.aimachines.cdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String SWAGGER_ADMIN_USER_ID = "swagger-admin-user";
	private static final String SWAGGER_CREDENTIAL_ADMIN = "swagger-credential-admin";

	private static final String SWAGGER_USER_ID = "swagger-user";
	private static final String SWAGGER_CREDENTIAL_USER = "swagger-credential-user";
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html",
						"/webjars/**", "/swagger-resources/configuration/ui")
				.permitAll().antMatchers("/v2/api-docs").hasRole("USER").and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(env.getProperty(SWAGGER_USER_ID)).password(passwordEncoder().encode(env.getProperty(SWAGGER_CREDENTIAL_USER)))
				.roles("USER").and().withUser(env.getProperty(SWAGGER_ADMIN_USER_ID)).password(passwordEncoder().encode(env.getProperty(SWAGGER_CREDENTIAL_ADMIN)))
				.roles("USER", "ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}