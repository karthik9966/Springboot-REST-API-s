package com.ttu.blogapplication.service.impl;

import com.ttu.blogapplication.exception.BlogApplicationException;
import com.ttu.blogapplication.exception.ResourceNotFoundException;
import com.ttu.blogapplication.model.Comment;
import com.ttu.blogapplication.model.Post;
import com.ttu.blogapplication.payload.CommentDTO;
import com.ttu.blogapplication.repository.CommentRepository;
import com.ttu.blogapplication.repository.PostRepository;
import com.ttu.blogapplication.service.CommentService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO save(long postId, CommentDTO comment) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "ID", Long.toString(postId))
        );
        Comment comment1 = convertToComment(comment);
        comment1.setPost(post);
        return convertToDTO(commentRepository.save(comment1));
    }

    @Override
    public CommentDTO getComment(long postId, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "ID", Long.toString(commentId))
        );
        if(!(comment.getPost().getId()==postId))
            throw new BlogApplicationException("Comment does not belong to post",HttpStatus.BAD_REQUEST);

        return convertToDTO(comment);
    }

    @Override
    public List<CommentDTO> getComments(int postId) {
       return commentRepository.findAllBypost_id(postId).stream().map(comment -> convertToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).get();
        if(!(comment.getPost().getId()==postId))
            throw new BlogApplicationException("Comment does not belong to post",HttpStatus.BAD_REQUEST);
        comment.setName(commentDTO.getName());
        comment.setBody(commentDTO.getBody());
        comment.setEmail(commentDTO.getEmail());
        return convertToDTO(commentRepository.save(comment));
    }

    @Override
    public void deleteById(long id, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", Long.toString(commentId)));
        if(comment.getPost().getId()==id) commentRepository.deleteById(id);
        else throw new BlogApplicationException("Comment does not belong to post",HttpStatus.BAD_REQUEST);
    }

    private Comment convertToComment(CommentDTO commentDTO)
    {
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        return comment;
    }

    private CommentDTO convertToDTO(Comment comment)
    {
        CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
        return commentDTO;
    }
}
