package com.application.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	
	
	

//	//add support for JDBC 
//	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		//define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select email, password, active from users where email=?"
				);
				
		//define query to retrieve the roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select email, role from roles where email=?"
				);
		return jdbcUserDetailsManager;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer ->
				configurer
					.requestMatchers(HttpMethod.GET, "/api/user").permitAll()
					.requestMatchers(HttpMethod.GET, "/api/user/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/api/user").permitAll()
					.requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
					
					
				);
		
		//use http basic authendication
		http.httpBasic(Customizer.withDefaults());
		
		//disable cross site request forgery
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	
	
	
	
	
	
	
	
	/*@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER")
				.build();
		
		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john, mary, susan);
				
	}*/
}
