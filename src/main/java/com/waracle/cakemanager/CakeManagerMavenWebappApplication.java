package com.waracle.cakemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class CakeManagerMavenWebappApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(CakeManagerMavenWebappApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/","/cakes/**","/cake/**", "/h2-console/**").permitAll()
				.anyRequest()
				.authenticated().and().oauth2Login();
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();
	}
}
