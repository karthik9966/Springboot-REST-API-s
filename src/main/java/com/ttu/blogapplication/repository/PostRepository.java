package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
