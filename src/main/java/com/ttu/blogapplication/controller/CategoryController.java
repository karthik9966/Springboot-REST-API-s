package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.payload.CategoryDto;
import com.ttu.blogapplication.payload.PostDTO;
import com.ttu.blogapplication.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Long id)
    {
        return categoryService.getCategory(id);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto)
    {
        return categoryService.updateCategory(id,categoryDto);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostDTO> getAllPostsByCategory(@PathVariable Long id)
    {
        return categoryService.getAllPostsByCategory(id);
    }
}
