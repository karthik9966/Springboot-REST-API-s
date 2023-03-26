package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.model.Comment;
import com.ttu.blogapplication.payload.CommentDTO;
import com.ttu.blogapplication.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<CommentDTO> getAllComments(@PathVariable int postId)
    {
        return commentService.getComments(postId);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable long postId,@PathVariable(name = "id") long commentId)
    {
        return new ResponseEntity<>(commentService.getComment(postId,commentId),HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> postComment(@PathVariable long postId, @Valid @RequestBody CommentDTO comment)
    {
        return new ResponseEntity<CommentDTO>(commentService.save(postId,comment), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long postId,
                                                    @PathVariable(name = "id") long commentId,
                                                    @Valid @RequestBody CommentDTO commentDTO)
    {
        return new ResponseEntity<>(commentService.updateComment(postId,commentId,commentDTO),HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable long postId,@PathVariable(name = "id") long commentId)
    {
        commentService.deleteById(postId,commentId);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }
}
