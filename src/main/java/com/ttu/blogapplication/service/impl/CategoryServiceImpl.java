package com.ttu.blogapplication.service.impl;

import com.ttu.blogapplication.exception.ResourceNotFoundException;
import com.ttu.blogapplication.model.Category;
import com.ttu.blogapplication.payload.CategoryDto;
import com.ttu.blogapplication.payload.PostDTO;
import com.ttu.blogapplication.repository.CategoryRepository;
import com.ttu.blogapplication.service.CategoryService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private ModelMapper modelMapper;

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "ID", Long.toString(id))
        );
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().
                stream().
                map(category -> modelMapper.map(category, CategoryDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "ID", Long.toString(id))
        );
        categoryDto.setId(id);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "ID", Long.toString(id))
        );
        categoryRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public List<PostDTO> getAllPostsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "ID", Long.toString(id))
        ); 
        return category.getPosts().stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }
}
