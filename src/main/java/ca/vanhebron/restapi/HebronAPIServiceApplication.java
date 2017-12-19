package ca.vanhebron.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAutoConfiguration
@SpringBootApplication
public class HebronAPIServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HebronAPIServiceApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//TODO
	//http://www.baeldung.com/rest-api-spring-oauth2-angularjs
	//https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql

	//https://github.com/spring-projects/spring-security-oauth/blob/master/docs/oauth2.md



	//https://hellokoding.com/registration-and-login-example-with-spring-xml-configuration-maven-jsp-and-mysql/


	//https://aykutakin.wordpress.com/2013/07/08/spring-security-spring-custom-authentication-provider/


	//https://github.com/Baeldung/spring-security-registration

}
