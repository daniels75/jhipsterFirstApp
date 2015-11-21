package org.daniels.jhipster.firstapp.repository;

import org.daniels.jhipster.firstapp.domain.Simple;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Simple entity.
 */
public interface SimpleRepository extends JpaRepository<Simple,Long> {

}
