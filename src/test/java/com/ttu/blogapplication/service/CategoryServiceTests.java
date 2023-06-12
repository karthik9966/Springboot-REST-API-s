package com.ttu.blogapplication.service;

import com.ttu.blogapplication.model.Category;
import com.ttu.blogapplication.payload.CategoryDto;
import com.ttu.blogapplication.repository.CategoryRepository;
import com.ttu.blogapplication.service.impl.CategoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

//    public CategoryDto createCategory(CategoryDto categoryDto) {
//        Category category = modelMapper.map(categoryDto, Category.class);
//        Category savedCategory = categoryRepository.save(category);
//        return modelMapper.map(savedCategory, CategoryDto.class);
//    }

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void givenCategoryDto_WhenCreateCategory_ThenReturnCategoryDto()
    {
        //Given
        CategoryDto categoryDto = new CategoryDto(1L,"Hello","Hi");
        Category category = new Category();
        category.setId(1L);
        category.setName("Hello");
        category.setDescription("Hi");
        Category savedCategory = category;
        BDDMockito.given(modelMapper.map(categoryDto, Category.class)).willReturn(category);
        BDDMockito.given(categoryRepository.save(category)).willReturn(savedCategory);
        BDDMockito.given(modelMapper.map(savedCategory, CategoryDto.class)).willReturn(categoryDto);

        //When

        CategoryDto category1 = categoryService.createCategory(categoryDto);

        //Then

        Assertions.assertThat(category1).isNotNull();
        Assertions.assertThat(category1).isEqualTo(categoryDto);
    }

}
