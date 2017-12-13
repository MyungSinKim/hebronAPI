package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.CellGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by rocky.lee on 2017-12-01.
 */
public interface CellGroupRepository extends CrudRepository<CellGroup, Long> {

//	@Query(value = "SELECT P.id," +
//			"P.first_name,P.last_name,P.gender,P.email," +
//			"P.telephone,P.role_id,P.service_id,P.photo," +
//			"P.birthday,P.status, P.cellgroup_id " +
//			"FROM hebron_api.cellgroup C " +
//			"JOIN hebron_api.person P " +
//			"ON C.leader_id = P.id " +
//			"WHERE C.id = :id", nativeQuery = true)
//	Person findCellGroupLeaderById(@Param("id") Integer id);


	@Query(value ="SELECT t.leader_id FROM cellgroup t where t.id = :id", nativeQuery = true)
	Integer findLeaderById(@Param("id") Integer id);

}
