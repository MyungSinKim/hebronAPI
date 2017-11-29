package ca.vanhebron.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by rocky.lee on 2017-11-29.
 */
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
