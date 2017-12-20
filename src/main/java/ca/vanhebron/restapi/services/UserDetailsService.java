package ca.vanhebron.restapi.services;

import static java.util.Objects.isNull;

import ca.vanhebron.restapi.entities.User;
import ca.vanhebron.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rocky.lee on 2017-12-18.
 */
@Service
public class UserDetailsService implements IUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (isNull(user)) {
			throw new BadCredentialsException("Username not found.");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(),
				grantedAuthorities);
	}

	public UsernamePasswordAuthenticationToken customAuthenticate(
			String name,
			String password,
			List<GrantedAuthority> grantedAuthorities)
			throws AuthenticationException {

		//		User userTemp = User.builder()
		//				.username("rocky.lee@payfiram.com")
		//				.password("password")
		//				.build();
		//
		//		userService.save(userTemp);

		User user = userRepository.findByUsername(name);

		if (isNull(user)) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!userService.isMatchedPassword(password, user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);

		return token;
	}
}
