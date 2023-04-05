package com.ttu.blogapplication.service;

import com.ttu.blogapplication.payload.PageableResponse;
import com.ttu.blogapplication.payload.PostDTO;

import java.util.List;

public interface PostService {
    public PostDTO save(PostDTO postDTO);

    List<PostDTO> getAll();

    PostDTO getById(long id);

    PostDTO updateById(long id,PostDTO postDTO);

    PageableResponse getAll(int pageNumber,int pageOffset,String feildName,String sortBy);

    void deleteById(long id);
}
