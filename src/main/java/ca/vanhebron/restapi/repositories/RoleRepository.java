package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rocky.lee on 2017-12-18.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
