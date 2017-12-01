package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by rocky.lee on 2017-11-29.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findAll();

	@Query("SELECT t.firstName FROM Person t where t.id = :id")
	String findTitleById(@Param("id") Integer id);


	//@Query("SELECT id,firstName,lastName ,gender, email,telephone, photo, role FROM Person t where t.firstName = :name OR t.lastName = :name")
	//@Query(value = "SELECT id,first_name,last_name,gender,email,telephone,role_id,service_id,photo FROM Person t where t.first_name = :name OR t.last_name = :name", nativeQuery = true)
	@Query(value = "SELECT * FROM person t where t.first_name = :name OR t.last_name = :name", nativeQuery = true)
	List<Person> findListByName(@Param("name") String name);


	//TODO:
	//https://www.javabullets.com/access-entitymanager-spring-data-jpa/



}
