package com.vote.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new  UserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/system_admin_home").hasAuthority("system admin")
		    .antMatchers("/roles").hasAuthority("system admin")
		    .antMatchers("/roles/new").hasAuthority("system admin")
		    .antMatchers("/roles/edit/**").hasAuthority("system admin")
		    .antMatchers("/roles/delete/**").hasAuthority("system admin")
		    .antMatchers("/permissions").hasAuthority("system admin")
		    .antMatchers("/permissions/new").hasAuthority("system admin")
		    .antMatchers("/permissions/edit/**").hasAuthority("system admin")
		    .antMatchers("/permissions/delete/**").hasAuthority("system admin")
		    .antMatchers("/users").hasAuthority("system admin")
		    .antMatchers("/users/new").hasAuthority("system admin")
		    .antMatchers("/users/edit/**").hasAuthority("system admin")
		    .antMatchers("/users/delete/**").hasAuthority("system admin")
		    
		    .antMatchers("/voting_admin_home").hasAuthority("voting admin")
		    .antMatchers("/students").hasAuthority("voting admin")
		    .antMatchers("/students/new").hasAuthority("voting admin")
		    .antMatchers("/students/edit/**").hasAuthority("voting admin")
		    .antMatchers("/students/delete/**").hasAuthority("voting admin")
		    .antMatchers("/elections").hasAuthority("voting admin")
		    .antMatchers("/elections/new").hasAuthority("voting admin")
		    .antMatchers("/elections/edit/**").hasAuthority("voting admin")
		    .antMatchers("/elections/delete/**").hasAuthority("voting admin")
		    .antMatchers("/options").hasAuthority("voting admin")
		    .antMatchers("/options/new").hasAuthority("voting admin")
		    .antMatchers("/options/edit/**").hasAuthority("voting admin")
		    .antMatchers("/options/delete/**").hasAuthority("voting admin")
		    .antMatchers("/groups").hasAuthority("voting admin")
		    .antMatchers("/groups/new").hasAuthority("voting admin")
		    .antMatchers("/groups/edit/**").hasAuthority("voting admin")
		    .antMatchers("/groups/delete/**").hasAuthority("voting admin")
		    
		    .antMatchers("/voter_home").hasAuthority("voter")
		    .antMatchers("/votes").hasAuthority("voter")
		    
			.anyRequest().permitAll() //any user have to be authenticated to use the application
			.and()
			.formLogin().loginPage("/login").permitAll() // any user can use the login form
						.successHandler(successHandler)
			.and()
			.logout().permitAll() //any user can logout
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	
}
