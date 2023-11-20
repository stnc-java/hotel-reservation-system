package com.aspire.webapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("SELECT username, password, 1 FROM users WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT username, role FROM users WHERE username = ?");
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/", "/home", "/css/**", "/assets/**", "/login", "/change-password", "/error").permitAll()
			.antMatchers("/main", "/guest-form", "/guests", "/add-guest", "/delete-guest-**", "/edit-guest-**", "/update-guest").hasAnyAuthority("ADMIN", "USER")
			.antMatchers().hasAnyAuthority("ADMIN")
			.anyRequest().authenticated() 
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/home")
			.permitAll();
	}
}
