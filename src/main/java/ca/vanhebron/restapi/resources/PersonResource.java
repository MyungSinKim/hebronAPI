package ca.vanhebron.restapi.resources;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import ca.vanhebron.restapi.entities.HebronPerson;
import ca.vanhebron.restapi.models.Gender;
import ca.vanhebron.restapi.models.PersonSearchFilter;
import ca.vanhebron.restapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "hebron/person")
public class PersonResource {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/all", method = GET)
	public ResponseEntity<List<HebronPerson>> getAllPerson() {
		return ResponseEntity.ok(personService.getAllPerson());
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<HebronPerson>> getPersonSearch(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "service_id", required = false) Long serviceId,
			@RequestParam(value = "cellgroup_id", required = false) Long cellgroupId,
			@RequestParam(value = "role_id", required = false) Long roleId,
			@RequestParam(value = "gender", required = false) Gender gender,
			Principal principal ) {

		PersonSearchFilter personSearchFilter = PersonSearchFilter.builder()
				.name(name)
				.serviceId(serviceId)
				.roleId(roleId)
				.cellgroupId(cellgroupId)
				.gender(gender)
				.build();


		//Object object  =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String loginUserName = principal.getName(); //get logged in username

		return ResponseEntity.ok(personService.getAllPersonSearch(personSearchFilter));
	}

	@RequestMapping(value = "/{personId}", method = GET)
	public ResponseEntity<HebronPerson> getPerson(@PathVariable Long personId) {
		return ResponseEntity.ok(personService.getPerson(personId));
	}

	@RequestMapping(method = POST)
	public ResponseEntity<HebronPerson> createPerson(
			@RequestBody HebronPerson personRequest) {
		return ResponseEntity.ok(personService.createPerson(personRequest));
	}

	@RequestMapping(value = "/{personId}", method = PUT)
	public ResponseEntity<HebronPerson> updatePerson(
			@PathVariable Long personId,
			@RequestBody HebronPerson personRequest) {
		return ResponseEntity.ok(personService.updatePerson(personId, personRequest));
	}

	@RequestMapping(value = "/{personId}", method = DELETE)
	public ResponseEntity deletePerson(@PathVariable Long personId) {
		personService.deletePerson(personId);
		return new ResponseEntity<>(ACCEPTED);
	}
}
