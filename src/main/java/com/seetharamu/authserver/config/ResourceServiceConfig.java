package com.seetharamu.authserver.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


@EnableResourceServer
@Configuration
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {

	/*@Value("${oauth.server.uri}")
	private String authServerUri;*/

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/users/user").authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user/user").access("hasAuthority('ROLE_admin')")
				.antMatchers("/*").permitAll()
				.anyRequest().authenticated();

	}

	@Bean
	public ResourceServerTokenServices tokenServices() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl( "http://localhost:8082/oauth/check_token");
		tokenService.setClientId("mobile");
		tokenService.setClientSecret("secret");
		return tokenService;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		String resourceIds = "inventory";
		resources.resourceId(resourceIds).tokenServices(tokenServices());
	}


}
