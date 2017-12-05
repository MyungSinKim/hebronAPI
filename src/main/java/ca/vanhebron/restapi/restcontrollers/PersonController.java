package ca.vanhebron.restapi.restcontrollers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import ca.vanhebron.restapi.entities.CellGroup;
import ca.vanhebron.restapi.entities.Person;
import ca.vanhebron.restapi.repositories.CellGroupRepository;
import ca.vanhebron.restapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by rocky.lee on 2017-11-29.
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/person") // This means URL's start with /demo (after Application path)
public class PersonController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CellGroupRepository cellGroupRepository;

	@RequestMapping(value = "/cellgroup", method = GET)
	public ResponseEntity<List<CellGroup>> getCellGroup() {
		List<CellGroup> list = (List<CellGroup>) cellGroupRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@RequestMapping(value = "/all", method = GET)
	public ResponseEntity<List<Person>> getPerson(@RequestParam(value = "name", defaultValue = "World") String name) {

	//	testFunction();

		List<Person> personList = personRepository.findAll();
		//List<Person> personList = personRepository.findListByName(name);
		return ResponseEntity.ok(personList);

		//return ResponseEntity.ok(testFunction());

	}

	private List<Person> testFunction() {
		Query query =
				entityManager.createNativeQuery(
						"SELECT person.id,first_name,last_name,gender,email,telephone,role_id,service_id,photo,birthday,status " +
								"FROM person JOIN role ON person.role_id = role.id WHERE role.name='Senior Pastor'",
						Person.class);
		List<Person> results = query.getResultList();
		//		for (Person c : results) {
		//			System.out.println(c.getFirstName() + " => " + c.getRole().getName());
		//		}

		return results;
	}

}
