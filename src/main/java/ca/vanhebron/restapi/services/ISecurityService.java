package ca.vanhebron.restapi.services;

/**
 * Created by rocky.lee on 2017-12-18.
 */
public interface ISecurityService {
	String findLoggedInUsername();

	void autologin(String username, String password);
}
