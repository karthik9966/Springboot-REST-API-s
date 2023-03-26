package com.ttu.blogapplication.service;

import com.ttu.blogapplication.payload.CategoryDto;
import com.ttu.blogapplication.payload.PostDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    String deleteCategory(Long id);

    List<PostDTO> getAllPostsByCategory(Long id);
}
