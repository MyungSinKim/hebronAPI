package ca.vanhebron.restapi.entities;

import ca.vanhebron.restapi.models.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

/**
 * Created by rocky.lee on 2017-11-29.
 */
@Entity // This tells Hibernate to make a table out of this class
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String firstName;
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String email;
	private String telephone;

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(targetEntity = Service.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service service;

	private String photo;

	//TODO
	// birthday
	// status (CANADIAN, KOREAN, PR, WORKING VISA, STUDENT, VISITOR
	//
}
