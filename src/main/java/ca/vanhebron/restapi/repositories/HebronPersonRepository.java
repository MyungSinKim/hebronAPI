package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.HebronPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HebronPersonRepository extends JpaRepository<HebronPerson, Long> {

	List<HebronPerson> findAll();

	@Query(value = "SELECT * FROM hebron_person t where t.first_name LIKE :name OR t.last_name LIKE :name", nativeQuery = true)
	List<HebronPerson> findListByName(@Param("name") String name);

}
