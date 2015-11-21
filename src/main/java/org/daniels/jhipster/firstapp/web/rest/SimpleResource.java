package org.daniels.jhipster.firstapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.daniels.jhipster.firstapp.domain.Simple;
import org.daniels.jhipster.firstapp.repository.SimpleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Simple.
 */
@RestController
@RequestMapping("/api")
public class SimpleResource {

    private final Logger log = LoggerFactory.getLogger(SimpleResource.class);

    @Inject
    private SimpleRepository simpleRepository;

    /**
     * POST  /simples -> Create a new simple.
     */
    @RequestMapping(value = "/simples",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Simple simple) throws URISyntaxException {
        log.debug("REST request to save Simple : {}", simple);
        if (simple.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new simple cannot already have an ID").build();
        }
        simpleRepository.save(simple);
        return ResponseEntity.created(new URI("/api/simples/" + simple.getId())).build();
    }

    /**
     * PUT  /simples -> Updates an existing simple.
     */
    @RequestMapping(value = "/simples",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Simple simple) throws URISyntaxException {
        log.debug("REST request to update Simple : {}", simple);
        if (simple.getId() == null) {
            return create(simple);
        }
        simpleRepository.save(simple);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /simples -> get all the simples.
     */
    @RequestMapping(value = "/simples",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Simple> getAll() {
        log.debug("REST request to get all Simples");
        return simpleRepository.findAll();
    }

    /**
     * GET  /simples/:id -> get the "id" simple.
     */
    @RequestMapping(value = "/simples/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Simple> get(@PathVariable Long id) {
        log.debug("REST request to get Simple : {}", id);
        return Optional.ofNullable(simpleRepository.findOne(id))
            .map(simple -> new ResponseEntity<>(
                simple,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /simples/:id -> delete the "id" simple.
     */
    @RequestMapping(value = "/simples/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Simple : {}", id);
        simpleRepository.delete(id);
    }
}
