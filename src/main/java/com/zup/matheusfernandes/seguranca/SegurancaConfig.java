package com.zup.matheusfernandes.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter{
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/propostas/**")
			.hasAuthority("SCOPE_propostas:read")
				.antMatchers(HttpMethod.GET, "/api/cartoes/**")
			.hasAuthority("SCOPE_cartoes:read")
				.antMatchers(HttpMethod.POST, "/api/cartoes/**")
			.hasAuthority("SCOPE_cartoes:write")
				.antMatchers(HttpMethod.POST, "/api/propostas/**")
			.hasAuthority("SCOPE_propostas:write")
				.antMatchers(HttpMethod.POST, "/cartoes/**")
			.hasAuthority("SCOPE_viagens:write")
		.anyRequest().authenticated()
		.and()
        	.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
	


}
