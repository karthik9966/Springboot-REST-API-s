package com.ttu.blogapplication.service.impl;

import com.ttu.blogapplication.exception.ResourceNotFoundException;
import com.ttu.blogapplication.model.Category;
import com.ttu.blogapplication.model.Post;
import com.ttu.blogapplication.payload.PageableResponse;
import com.ttu.blogapplication.payload.PostDTO;
import com.ttu.blogapplication.repository.PostRepository;
import com.ttu.blogapplication.service.PostService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public PostServiceImpl(PostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO save(PostDTO postDTO)
    {
        return mapToDTO(postRepository.save(mapToPost(postDTO)));
    }

    @Override
    public List<PostDTO> getAll()
    {
       return postRepository.findAll().stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(long id) {
        return mapToDTO(postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Post","ID",Long.toString(id))));
    }

    @Override
    public PostDTO updateById(long id,PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",Long.toString(id)));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setCategory(modelMapper.map(postDTO.getCategory(), Category.class));
        postDTO.setId(post.getId());
        postRepository.save(post);
        return postDTO;
    }

    @Override
    public PageableResponse getAll(int pageNumber, int pageOffset, String feildName,String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase("asc") ? Sort.by(feildName).ascending() : Sort.by(feildName).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageOffset, sort);
        Page<Post> pages = postRepository.findAll(pageable);
        List<Post> posts = pages.getContent();
        List<PostDTO> postDTOs = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setPostDTOS(postDTOs);
        pageableResponse.setLastPage(pages.isLast());
        pageableResponse.setPageNumber(pages.getNumber());
        pageableResponse.setTotalPages(pages.getTotalPages());
        pageableResponse.setTotalItems(pages.getTotalElements());
        return pageableResponse;
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(postRepository.findById(id).orElseThrow(()->
                        new ResourceNotFoundException("Post","ID",Long.toString(id))
                ).getId());
    }

    private PostDTO mapToDTO(Post post)
    {
        PostDTO newPostDTO = modelMapper.map(post,PostDTO.class);
        return newPostDTO;
    }

    private Post mapToPost(PostDTO postDTO)
    {
        Post post = modelMapper.map(postDTO,Post.class);
        return post;
    }
}
