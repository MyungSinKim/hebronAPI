package ca.vanhebron.restapi.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocky.lee on 2017-12-15.
 */
public class OAuth2ServerConfig {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources)
				throws Exception {
			resources.tokenStore(tokenStore);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/hebron/**").authenticated();
		}
	}

	//	@Configuration
	//	public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
	//
	//		@Override
	//		protected void configure(AuthenticationManagerBuilder auth)
	//				throws Exception {
	//			auth.inMemoryAuthentication()
	//					.withUser("john").password("123").roles("USER");
	//		}
	//
	//		@Override
	//		@Bean
	//		public AuthenticationManager authenticationManagerBean()
	//				throws Exception {
	//			return super.authenticationManagerBean();
	//		}
	//
	//		@Override
	//		protected void configure(HttpSecurity http) throws Exception {
	//			http.authorizeRequests()
	//					.antMatchers("/login").permitAll()
	//					.anyRequest().authenticated()
	//					.and()
	//					.formLogin().permitAll();
	//		}
	//	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private AuthenticationManager auth;

		@Autowired
		private DataSource dataSource;

		@Autowired
		private ClientDetailsService clientDetailsService;

		private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		@Bean
		public JdbcTokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Bean
		protected AuthorizationCodeServices authorizationCodeServices() {
			return new JdbcAuthorizationCodeServices(dataSource);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security)
				throws Exception {
			security.passwordEncoder(passwordEncoder);
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.authorizationCodeServices(authorizationCodeServices())
					.authenticationManager(auth).tokenStore(tokenStore())
					.tokenServices(tokenServices())
					.approvalStoreDisabled();
		}

		@Bean
		@Primary
		public AuthorizationServerTokenServices tokenServices() {
			final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

			defaultTokenServices.setTokenStore(tokenStore());
			defaultTokenServices.setClientDetailsService(clientDetailsService);
			defaultTokenServices.setSupportRefreshToken(true);
			return defaultTokenServices;
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients.jdbc(dataSource);
			//					.passwordEncoder(passwordEncoder)
			//					.withClient("my-trusted-client")
			//					.authorizedGrantTypes("password", "authorization_code",
			//							"refresh_token", "implicit")
			//					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			//					.scopes("read", "write", "trust")
			//					.resourceIds("oauth2-resource")
			//					.accessTokenValiditySeconds(60).and()
			//					.withClient("my-client-with-registered-redirect")
			//					.authorizedGrantTypes("authorization_code")
			//					.authorities("ROLE_CLIENT").scopes("read", "trust")
			//					.resourceIds("oauth2-resource")
			//					.redirectUris("http://anywhere?key=value").and()
			//					.withClient("my-client-with-secret")
			//					.authorizedGrantTypes("client_credentials", "password")
			//					.authorities("ROLE_CLIENT").scopes("read")
			//					.resourceIds("oauth2-resource").secret("secret");
			// @formatter:on
		}

	}

	@Configuration
	protected static class AuthenticationManagerConfiguration extends
															  GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private CustomAuthenticationProvider customProvider;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customProvider);
		}
	}
}
