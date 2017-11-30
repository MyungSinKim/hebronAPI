package ca.vanhebron.restapi.restcontrollers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import ca.vanhebron.restapi.entities.Person;
import ca.vanhebron.restapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by rocky.lee on 2017-11-29.
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/person") // This means URL's start with /demo (after Application path)
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = "/all", method = GET)
	public ResponseEntity<List<Person>> getPerson(@RequestParam(value = "name", defaultValue = "World") String name) {

		//List<Person> personList = personRepository.findAll();

		List<Person> personList = personRepository.findListByName(name);

		return ResponseEntity.ok(personList);
	}

}
