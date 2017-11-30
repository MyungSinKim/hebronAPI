package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.Service;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by rocky.lee on 2017-11-29.
 */
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
