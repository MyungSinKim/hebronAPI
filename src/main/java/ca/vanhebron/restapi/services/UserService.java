package ca.vanhebron.restapi.services;

import ca.vanhebron.restapi.entities.User;
import ca.vanhebron.restapi.repositories.RoleRepository;
import ca.vanhebron.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rocky.lee on 2017-12-15.
 */
@Service
public class UserService implements IUserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public boolean isMatchedPassword(String password, String hashedPassword) {
		return bCryptPasswordEncoder.matches(password, hashedPassword);
	}

	@Override
	@Transactional
	public User createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User result = userRepository.save(user);
		entityManager.refresh(result);
		return result;
	}

	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}
}
