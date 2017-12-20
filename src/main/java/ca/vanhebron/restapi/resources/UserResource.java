package ca.vanhebron.restapi.resources;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import ca.vanhebron.restapi.entities.User;
import ca.vanhebron.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rocky.lee on 2017-12-19.
 */
@RestController
@RequestMapping(path = "hebron/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/all", method = GET)
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUser());
	}

	@RequestMapping(method = POST)
	public ResponseEntity<User> createUser(
			@RequestBody User userRequest) {
		return ResponseEntity.ok(userService.createUser(userRequest));
	}

	@RequestMapping(value = "/{userId}", method = DELETE)
	public ResponseEntity deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(ACCEPTED);
	}
}
