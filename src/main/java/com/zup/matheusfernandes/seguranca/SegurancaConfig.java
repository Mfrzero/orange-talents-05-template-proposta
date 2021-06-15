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
        	.authorizeRequests()
        		.antMatchers(HttpMethod.GET, "/api/propostas/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/cartoes/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/cartoes/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/propostas/**").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .anyRequest().authenticated();
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
