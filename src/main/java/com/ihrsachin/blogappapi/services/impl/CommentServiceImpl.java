package com.ihrsachin.blogappapi.services.impl;

import com.ihrsachin.blogappapi.entities.Comment;
import com.ihrsachin.blogappapi.entities.Post;
import com.ihrsachin.blogappapi.exceptions.ResourceNotFoundException;
import com.ihrsachin.blogappapi.payloads.CommentDto;
import com.ihrsachin.blogappapi.repositories.CommentRepo;
import com.ihrsachin.blogappapi.repositories.PostRepo;
import com.ihrsachin.blogappapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepo.delete(com);
	}

}
