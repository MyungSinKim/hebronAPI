package ca.vanhebron.restapi.configurations;

import ca.vanhebron.restapi.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

/**
 * Created by rocky.lee on 2017-12-15.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	@Autowired
	public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws
			AuthenticationException {

		return userDetailsService.customAuthenticate(
				authentication.getName(),
				authentication.getCredentials().toString(),
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

		//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getName(),
		//				authentication.getCredentials().toString(),
		//				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
		//
		//		System.out.println("CustomAuthenticationProvider");
		//
		//		return token;
		//https://aykutakin.wordpress.com/2013/07/08/spring-security-spring-custom-authentication-provider/

		//https://github.com/Baeldung/spring-security-registration

		//https://github.com/Baeldung/spring-security-oauth/blob/master/spring-security-oauth-server/src/main/resources/data.sql

		//https://www.codebyamir.com/blog/forgot-password-feature-with-java-and-spring-boot

		//https://github.com/hellokoding/registration-login-spring-xml-maven-jsp-mysql/blob/master/src/main/java/com/hellokoding/account/service/UserService.java
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
