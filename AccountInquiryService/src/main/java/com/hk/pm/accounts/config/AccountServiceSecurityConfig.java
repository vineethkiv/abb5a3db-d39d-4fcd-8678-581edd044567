package com.hk.pm.accounts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hk.pm.accounts.security.AccountAuthenticationEntryPoint;
import com.hk.pm.accounts.security.AccountAuthenticationFilter;
import com.hk.pm.accounts.security.AccountLoginFilter;

/**
 * @author Vineeth Kiv
 * Config class for web security
 *
 */
@Configuration
@EnableWebSecurity
public class AccountServiceSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("account-service-admin").password("12345678").roles("ADMIN");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/login")
				.permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new AccountLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new AccountAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(new AccountAuthenticationEntryPoint());
		
	}

}
