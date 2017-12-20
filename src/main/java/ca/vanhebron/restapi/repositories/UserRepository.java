package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rocky.lee on 2017-12-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	List<User> findAll();
}
