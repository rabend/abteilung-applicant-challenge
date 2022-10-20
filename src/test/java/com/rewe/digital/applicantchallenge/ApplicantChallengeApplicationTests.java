package com.rewe.digital.applicantchallenge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewe.digital.applicantchallenge.model.CategoryDTO;
import com.rewe.digital.applicantchallenge.model.CategoryEntity;
import com.rewe.digital.applicantchallenge.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicantChallengeApplicationTests {

    private final TestRestTemplate testRestTemplate;

    private final ObjectMapper mapper;

    private final CategoryRepository categoryRepository;

    @LocalServerPort
    private String port;

    @Value("classpath:testdata.json")
    private Resource testData;

    @Autowired
    public ApplicantChallengeApplicationTests(
            TestRestTemplate testRestTemplate,
            ObjectMapper mapper,
            CategoryRepository categoryRepository) {
        this.testRestTemplate = testRestTemplate;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    public void setupSpec() {
        try {
            var categories = mapper.readValue(testData.getFile(), new TypeReference<List<CategoryEntity>>() {
            });
            categoryRepository.saveAll(categories);
        } catch (Exception exception) {
            fail("Setup went wrong");
        }
    }

    @Test
    void shouldGetCategoryTree() {
        var type = new ParameterizedTypeReference<List<CategoryDTO>>() {
        };
        var response = testRestTemplate.exchange("http://localhost:" + port + "/categories", HttpMethod.GET, null, type);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        var categories = response.getBody();
        assertNotNull(categories);
        assertEquals(categories.size(), 1);

        var category = categories.get(0);
        assertEquals(category.getId(), "921");
        assertEquals(category.getName(), "Getränke");
        assertEquals(category.getSlug(), "getraenke");

        var firstGen = category.getChildren();
        assertEquals(
                firstGen.stream().map(CategoryDTO::getId).collect(Collectors.toList()),
                Arrays.asList("964", "985")
        );

        var secondGen = firstGen.stream().filter(it -> it.getId().equals("985"))
                .findFirst()
                .get()
                .getChildren();
        assertEquals(
                secondGen.stream().map(CategoryDTO::getId).collect(Collectors.toList()),
                Arrays.asList("986", "994", "997")
        );
    }

}
