package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findAllBypost_id(long postId);
}
