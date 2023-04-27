package com.api.cotacaodolar;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CotacaoDolarApplication.class)
@ActiveProfiles(resolver = SpringActiveProfileResolver.class)
public abstract class AbstractCotacaoDolarApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private ObjectMapper jsonMapper;

	protected PayloadExtractor payloadExtractor;

	@BeforeEach
	public void init() throws Exception {
		payloadExtractor = new PayloadExtractor(jsonMapper);
	}

}
