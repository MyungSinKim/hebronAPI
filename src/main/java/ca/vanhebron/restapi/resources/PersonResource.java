package ca.vanhebron.restapi.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import ca.vanhebron.restapi.entities.Person;
import ca.vanhebron.restapi.models.Gender;
import ca.vanhebron.restapi.models.PersonSearchFilter;
import ca.vanhebron.restapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonResource {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/all", method = GET)
	public ResponseEntity<List<Person>> getAllPerson() {
		return ResponseEntity.ok(personService.getAllPerson());
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<Person>> getPersonSearch(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "service_id", required = false) Long serviceId,
			@RequestParam(value = "cellgroup_id", required = false) Long cellgroupId,
			@RequestParam(value = "role_id", required = false) Long roleId,
			@RequestParam(value = "gender", required = false) Gender gender) {

		PersonSearchFilter personSearchFilter = PersonSearchFilter.builder()
				.name(name)
				.serviceId(serviceId)
				.roleId(roleId)
				.cellgroupId(cellgroupId)
				.gender(gender)
				.build();

		return ResponseEntity.ok(personService.getAllPersonSearch(personSearchFilter));
	}

	@RequestMapping(value = "/{personId}", method = GET)
	public ResponseEntity<Person> getPerson(@PathVariable Long personId) {
		return ResponseEntity.ok(personService.getPerson(personId));
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Person> createPerson(
			@RequestBody Person personRequest) {
		return ResponseEntity.ok(personService.createPerson(personRequest));
	}

	@RequestMapping(value = "/{personId}", method = PUT)
	public ResponseEntity<Person> updatePerson(
			@PathVariable Long personId,
			@RequestBody Person personRequest) {
		return ResponseEntity.ok(personService.updatePerson(personId, personRequest));
	}
}
