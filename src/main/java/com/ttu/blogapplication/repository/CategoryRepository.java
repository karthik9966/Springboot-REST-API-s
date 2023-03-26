package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
