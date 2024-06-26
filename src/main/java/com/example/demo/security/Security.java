package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		
		.usersByUsernameQuery("select username,password,enabled from tbl_user where username=? ")
//		.usersByUsernameQuery("select email,enabled,password,username,vendor_id from tbl_user where username=?")
		.authoritiesByUsernameQuery(" select username,role from tbl_user where username=? ")
		
		.passwordEncoder(new BCryptPasswordEncoder())
		;
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationprovider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//		.antMatchers("/**","/usertype/**","/vendortype/**").hasRole("Admin")
//		.antMatchers("/vendor/**","/regulation/**").hasAnyRole("Quality","Social","Admin")
//		.antMatchers("/").permitAll()
		.anyRequest().permitAll()
		.and()
		
		.logout()
		.invalidateHttpSession(true)
		
		.and()
		.httpBasic();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passEncoder()
	{ 
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
	}
}
