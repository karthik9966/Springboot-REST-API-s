package com.ttu.blogapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttu.blogapplication.filters.JwtFilter;
import com.ttu.blogapplication.payload.CategoryDto;
import com.ttu.blogapplication.service.CategoryService;
import com.ttu.blogapplication.utils.JwtTokenProvider;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCategory_whenCreateCategory_thenReturnCategory() throws Exception {
        //Given

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("ABC");
        categoryDto.setDescription("ABC");

        BDDMockito.given(categoryService.createCategory(ArgumentMatchers.any(CategoryDto.class))).
                willAnswer((invocation) -> invocation.getArgument(0));

        //When

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categories").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(categoryDto)));

        //Then

        result.andDo(MockMvcResultHandlers.print()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(categoryDto.getName())));

    }
}
