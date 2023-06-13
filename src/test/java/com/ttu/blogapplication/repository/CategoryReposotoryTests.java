package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryReposotoryTests {


    @Autowired
    private CategoryRepository categoryRepository;

    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenCategoryObject_whenSave_thenReturnSavedcategory(){

        //given - precondition or setup
        Category category = new Category();
        category.setId(1);
        category.setName("Test category");
        category.setDescription("Test Category Description");
        // when - action or the behaviour that we are going test
        Category savedCategory = categoryRepository.save(category);

        // then - verify the output
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isEqualTo(1);
    }
}
