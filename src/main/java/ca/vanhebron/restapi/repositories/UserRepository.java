package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rocky.lee on 2017-11-28.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
