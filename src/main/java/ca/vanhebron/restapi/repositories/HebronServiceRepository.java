package ca.vanhebron.restapi.repositories;

import ca.vanhebron.restapi.entities.HebronService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HebronServiceRepository extends JpaRepository<HebronService, Long> {
}
