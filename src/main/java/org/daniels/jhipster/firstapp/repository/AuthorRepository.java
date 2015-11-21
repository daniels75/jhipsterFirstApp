package org.daniels.jhipster.firstapp.repository;

import org.daniels.jhipster.firstapp.domain.Author;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Author entity.
 */
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
