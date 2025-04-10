package com.security.AuthSystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.security.AuthSystem.service.CustomSuccessHandler;
import com.security.AuthSystem.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private CustomUserDetailsService customUserDetailsService;
    private PasswordEncoder passwordEncoder;
    private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,PasswordEncoder passwordEncoder,CustomSuccessHandler customSuccessHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.customSuccessHandler = customSuccessHandler;
	}
	

	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	
    	http.csrf(c -> c.disable())
    	.authorizeHttpRequests(request -> request.
    			requestMatchers("/admin/**").hasAuthority("ADMIN").
    			requestMatchers("/user/**").hasAuthority("USER").
    			requestMatchers("/registration").permitAll().
    			requestMatchers("/style.css", "/css/**", "/js/**", "/images/**").permitAll().
    			anyRequest().authenticated())
    	
    	.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").successHandler(customSuccessHandler).permitAll())
    	
    	.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
    	
    	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	
    	.logoutSuccessUrl("/login?logout").permitAll());
    	
    	
    	return http.build();
    }
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}
}
