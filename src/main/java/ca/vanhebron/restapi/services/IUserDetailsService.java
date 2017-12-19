package ca.vanhebron.restapi.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by rocky.lee on 2017-12-18.
 */
public interface IUserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
