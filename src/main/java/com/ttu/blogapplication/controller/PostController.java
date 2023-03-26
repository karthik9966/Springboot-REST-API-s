package com.ttu.blogapplication.controller;

import com.ttu.blogapplication.payload.PageableResponse;
import com.ttu.blogapplication.payload.PostDTO;
import com.ttu.blogapplication.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    PostController(PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts()
    {
        return new ArrayList<PostDTO>(postService.getAll());
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable long id)
    {
        return postService.getById(id);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> savePost(@Valid @RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.save(postDTO),HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable long id,@Valid @RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.updateById(id,postDTO),HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id)
    {
        postService.deleteById(id);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }

    @GetMapping("/pagingAndSorting")
    public PageableResponse getAll(@RequestParam(required = false,defaultValue = "0") int pageNumber,
                                   @RequestParam(required = false,defaultValue = "5") int pageOffset,
                                   @RequestParam(required = false,defaultValue = "id") String feildName,
                                   @RequestParam(required = false,defaultValue = "asc") String sortBy)
    {
        return postService.getAll(pageNumber,pageOffset,feildName,sortBy);
    }
}
