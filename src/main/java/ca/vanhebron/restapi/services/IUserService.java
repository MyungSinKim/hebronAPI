package ca.vanhebron.restapi.services;

import ca.vanhebron.restapi.entities.User;

import java.util.List;

/**
 * Created by rocky.lee on 2017-12-18.
 */
public interface IUserService {
	void save(User user);

	User findByUsername(String username);

	List<User> getAllUser();

	User createUser(User user);
}
