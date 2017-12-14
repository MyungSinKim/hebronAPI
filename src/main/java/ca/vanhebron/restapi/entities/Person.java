package ca.vanhebron.restapi.entities;

import ca.vanhebron.restapi.models.Gender;
import ca.vanhebron.restapi.models.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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
	private Long id;

	private String firstName;
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String email;
	private String telephone;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "service_id")
	private Long serviceId;

	@Column(name = "cellgroup_id")
	private Long cellgroupId;

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@ManyToOne(targetEntity = Service.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", insertable = false, updatable = false)
	private Service service;

	private String photo;

	private Date birthday;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(targetEntity = CellGroup.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cellgroup_id", insertable = false, updatable = false)
	private CellGroup cellGroup;

	@Override
	public String toString() {
		return "Person[id=" + id + ", firstName=" + firstName
				+ "]";
	}
}
