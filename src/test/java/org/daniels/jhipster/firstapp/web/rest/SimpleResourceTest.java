package org.daniels.jhipster.firstapp.web.rest;

import org.daniels.jhipster.firstapp.Application;
import org.daniels.jhipster.firstapp.domain.Simple;
import org.daniels.jhipster.firstapp.repository.SimpleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SimpleResource REST controller.
 *
 * @see SimpleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SimpleResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_MODEL = "SAMPLE_TEXT";
    private static final String UPDATED_MODEL = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(1);

    @Inject
    private SimpleRepository simpleRepository;

    private MockMvc restSimpleMockMvc;

    private Simple simple;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SimpleResource simpleResource = new SimpleResource();
        ReflectionTestUtils.setField(simpleResource, "simpleRepository", simpleRepository);
        this.restSimpleMockMvc = MockMvcBuilders.standaloneSetup(simpleResource).build();
    }

    @Before
    public void initTest() {
        simple = new Simple();
        simple.setName(DEFAULT_NAME);
        simple.setModel(DEFAULT_MODEL);
        simple.setPrice(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createSimple() throws Exception {
        int databaseSizeBeforeCreate = simpleRepository.findAll().size();

        // Create the Simple
        restSimpleMockMvc.perform(post("/api/simples")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(simple)))
                .andExpect(status().isCreated());

        // Validate the Simple in the database
        List<Simple> simples = simpleRepository.findAll();
        assertThat(simples).hasSize(databaseSizeBeforeCreate + 1);
        Simple testSimple = simples.get(simples.size() - 1);
        assertThat(testSimple.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSimple.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testSimple.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(simpleRepository.findAll()).hasSize(0);
        // set the field null
        simple.setName(null);

        // Create the Simple, which fails.
        restSimpleMockMvc.perform(post("/api/simples")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(simple)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<Simple> simples = simpleRepository.findAll();
        assertThat(simples).hasSize(0);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(simpleRepository.findAll()).hasSize(0);
        // set the field null
        simple.setModel(null);

        // Create the Simple, which fails.
        restSimpleMockMvc.perform(post("/api/simples")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(simple)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<Simple> simples = simpleRepository.findAll();
        assertThat(simples).hasSize(0);
    }

    @Test
    @Transactional
    public void getAllSimples() throws Exception {
        // Initialize the database
        simpleRepository.saveAndFlush(simple);

        // Get all the simples
        restSimpleMockMvc.perform(get("/api/simples"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(simple.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getSimple() throws Exception {
        // Initialize the database
        simpleRepository.saveAndFlush(simple);

        // Get the simple
        restSimpleMockMvc.perform(get("/api/simples/{id}", simple.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(simple.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSimple() throws Exception {
        // Get the simple
        restSimpleMockMvc.perform(get("/api/simples/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSimple() throws Exception {
        // Initialize the database
        simpleRepository.saveAndFlush(simple);

		int databaseSizeBeforeUpdate = simpleRepository.findAll().size();

        // Update the simple
        simple.setName(UPDATED_NAME);
        simple.setModel(UPDATED_MODEL);
        simple.setPrice(UPDATED_PRICE);
        restSimpleMockMvc.perform(put("/api/simples")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(simple)))
                .andExpect(status().isOk());

        // Validate the Simple in the database
        List<Simple> simples = simpleRepository.findAll();
        assertThat(simples).hasSize(databaseSizeBeforeUpdate);
        Simple testSimple = simples.get(simples.size() - 1);
        assertThat(testSimple.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSimple.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testSimple.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void deleteSimple() throws Exception {
        // Initialize the database
        simpleRepository.saveAndFlush(simple);

		int databaseSizeBeforeDelete = simpleRepository.findAll().size();

        // Get the simple
        restSimpleMockMvc.perform(delete("/api/simples/{id}", simple.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Simple> simples = simpleRepository.findAll();
        assertThat(simples).hasSize(databaseSizeBeforeDelete - 1);
    }
}
