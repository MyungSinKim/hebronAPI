package ca.vanhebron.restapi.services;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import ca.vanhebron.restapi.entities.Person;
import ca.vanhebron.restapi.exceptions.CustomException;
import ca.vanhebron.restapi.models.PersonSearchFilter;
import ca.vanhebron.restapi.repositories.CellGroupRepository;
import ca.vanhebron.restapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocky.lee on 2017-12-13.
 */
@Service
public class PersonService {

	private static final String SELECT_QUERY = "SELECT id,first_name,last_name,gender,email,telephone," +
			"role_id,service_id,cellgroup_id,photo,birthday,status " +
			"FROM Person ";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CellGroupRepository cellGroupRepository;

	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public List<Person> getAllPersonSearch(PersonSearchFilter personSearchFilter) {
		List<String> whereConditions = new ArrayList<>();
		String condition = "";

		if (nonNull(personSearchFilter.getName())) {
			condition = " (first_name LIKE '%" + personSearchFilter.getName() + "%' OR last_name LIKE '%" +
					personSearchFilter.getName() + "%') ";

			whereConditions.add(condition);
		}

		if (nonNull(personSearchFilter.getRoleId())) {
			condition = " role_id = " + personSearchFilter.getRoleId();
			whereConditions.add(condition);
		}

		if (nonNull(personSearchFilter.getServiceId())) {
			condition = " service_id = " + personSearchFilter.getServiceId();
			whereConditions.add(condition);
		}

		if (nonNull(personSearchFilter.getCellgroupId())) {
			condition = " cellgroup_id = " + personSearchFilter.getCellgroupId();
			whereConditions.add(condition);
		}

		String finalWhereConditions = " ";
		if (whereConditions.size() > 0) {
			finalWhereConditions = " WHERE " + String.join(" AND ", whereConditions);
		}

		Query query = entityManager.createNativeQuery(
				SELECT_QUERY + finalWhereConditions,
				Person.class);

		List<Person> results = query.getResultList();
		return results;
	}

	@Transactional
	public Person createPerson(Person person) {
		Person result = personRepository.save(person);
		entityManager.refresh(result);
		return result;
	}

	@Transactional
	public Person updatePerson(Long personId, Person updatePersonData) {
		Person originalData = personRepository.findOne(personId);

		if (isNull(originalData)) {
			throw new CustomException("Can not find the person with Id");
		}

		originalData.setFirstName(updatePersonData.getFirstName());
		originalData.setLastName(updatePersonData.getLastName());
		originalData.setGender(updatePersonData.getGender());
		originalData.setEmail(updatePersonData.getEmail());
		originalData.setTelephone(updatePersonData.getTelephone());
		originalData.setRoleId(updatePersonData.getRoleId());
		originalData.setServiceId(updatePersonData.getServiceId());
		originalData.setCellgroupId(updatePersonData.getCellgroupId());
		originalData.setPhoto(updatePersonData.getPhoto());
		originalData.setBirthday(updatePersonData.getBirthday());
		originalData.setStatus(updatePersonData.getStatus());
		return personRepository.save(originalData);
	}

	public Person getPerson(Long personId) {
		return personRepository.findOne(personId);
	}
}
