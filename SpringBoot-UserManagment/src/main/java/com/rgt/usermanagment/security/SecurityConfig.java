package com.rgt.usermanagment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This method admin can access the user details
	 */
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().permitAll().and()
				.httpBasic().and().csrf().disable();
	}
    
    /**
     * This method gives the Admin access to the user with the userName password
     */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.inMemoryAuthentication().withUser("vikas").password(passwordEncoder().encode("vikas123")).roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
