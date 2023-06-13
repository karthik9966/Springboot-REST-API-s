package com.ttu.blogapplication;

import com.ttu.blogapplication.model.Category;
import com.ttu.blogapplication.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationIntegrationTests {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenListOfCategories_whenGetAllCategories_thenReturnCategories() throws Exception{
		// given - precondition or setup
		List<Category> listOfCategories = new ArrayList<>();
		Category category1 = new Category();
		Category category2 = new Category();
		category1.setId(1);
		category1.setName("Test Name");
		category1.setDescription("Test category description");
		category2.setId(2);
		category2.setName("Test Name 2");
		category2.setDescription("Test category 2 description");
		listOfCategories.add(category1);
		listOfCategories.add(category2);
		categoryRepository.saveAll(listOfCategories);
		// when -  action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/categories"));

		// then - verify the output
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()",
						is(listOfCategories.size())));
	}
}