package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rocky.lee on 2017-11-29.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findAll();

	@Query(value = "SELECT * FROM person t where t.first_name LIKE :name OR t.last_name LIKE :name", nativeQuery = true)
	List<Person> findListByName(@Param("name") String name);

}
