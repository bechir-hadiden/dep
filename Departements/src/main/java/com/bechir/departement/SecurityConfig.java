package com.bechir.departement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

	public class SecurityConfig {
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder passwordEncoder = passwordEncoder();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .authorities("ADMIN")
                .build();

        UserDetails userBechir = User.withDefaultPasswordEncoder()
                .username("bechir")
                .password("123")
                .authorities("AGENT","USER")
                .build();

        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("123")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, userBechir, user1);
    }
	 
	 
	 @Bean
	 SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception 
	 {
	  http.authorizeHttpRequests((requests)->requests
	  
	 .requestMatchers("/showCreate","/saveDepartement","/modifierDepartement","/showCreatecollege","/saveCollege" ,"/supprimerDepartement","/supprimerCollege","/modifierCollege").hasAnyAuthority("ADMIN")
	 
	 .requestMatchers("/showCreate","/saveDepartement","/showCreatecollege" ,"/saveCollege").hasAnyAuthority("ADMIN","AGENT")

	  
	 .requestMatchers("/listeDepartement","/listCollege").hasAnyAuthority("ADMIN","AGENT","USER")
	 .anyRequest().authenticated())
	  
	  .formLogin(Customizer.withDefaults())
	  .httpBasic(Customizer.withDefaults())
	  .exceptionHandling((exception)-> 
	  exception.accessDeniedPage("/accessDenied"));
	  return http.build();
	 }
	 
//		@Bean
//		 public PasswordEncoder passwordEncoder () {
//		 return new BCryptPasswordEncoder();
//		 }


	}



