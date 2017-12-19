package ca.vanhebron.restapi.services;

import static ca.vanhebron.restapi.constants.CustomConstants.CANNOT_FIND_PERSON_BY_ID;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import ca.vanhebron.restapi.entities.HebronPerson;
import ca.vanhebron.restapi.exceptions.CustomException;
import ca.vanhebron.restapi.models.PersonSearchFilter;
import ca.vanhebron.restapi.repositories.HebronCellGroupRepository;
import ca.vanhebron.restapi.repositories.HebronPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

	private static final String SELECT_QUERY = "SELECT id,first_name,last_name,gender,email,telephone," +
			"role_id,service_id,cellgroup_id,photo,birthday,status " +
			"FROM hebron_person ";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private HebronPersonRepository personRepository;

	@Autowired
	private HebronCellGroupRepository cellGroupRepository;

	public List<HebronPerson> getAllPerson() {
		return personRepository.findAll();
	}

	public List<HebronPerson> getAllPersonSearch(PersonSearchFilter personSearchFilter) {
		List<String> whereConditions = new ArrayList<>();
		String condition = "";

		if (nonNull(personSearchFilter.getName()) && !personSearchFilter.getName().isEmpty()) {
			condition = " (first_name LIKE '%" + personSearchFilter.getName() + "%' OR last_name LIKE '%" +
					personSearchFilter.getName() + "%') ";
			whereConditions.add(condition);
		}

		if (nonNull(personSearchFilter.getRoleId())) {
			whereConditions.add(" role_id = " + personSearchFilter.getRoleId());
		}

		if (nonNull(personSearchFilter.getServiceId())) {
			whereConditions.add(" service_id = " + personSearchFilter.getServiceId());
		}

		if (nonNull(personSearchFilter.getCellgroupId())) {
			whereConditions.add(" cellgroup_id = " + personSearchFilter.getCellgroupId());
		}

		String finalWhereConditions = " ";
		if (whereConditions.size() > 0) {
			finalWhereConditions = " WHERE " + String.join(" AND ", whereConditions);
		}

		Query query = entityManager.createNativeQuery(
				SELECT_QUERY + finalWhereConditions,
				HebronPerson.class);

		List<HebronPerson> results = query.getResultList();
		return results;
	}

	@Transactional
	public HebronPerson createPerson(HebronPerson person) {
		HebronPerson result = personRepository.save(person);
		entityManager.refresh(result);
		return result;
	}

	@Transactional
	public HebronPerson updatePerson(Long personId, HebronPerson updatePersonData) {
		HebronPerson originalData = personRepository.findOne(personId);

		if (isNull(originalData)) {
			throw new CustomException(CANNOT_FIND_PERSON_BY_ID);
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

	public HebronPerson getPerson(Long personId) {
		HebronPerson result = personRepository.findOne(personId);
		if (isNull(result)) {
			throw new CustomException(CANNOT_FIND_PERSON_BY_ID);
		}
		return result;
	}

	public void deletePerson(Long personId) {
		personRepository.delete(personId);
	}
}
