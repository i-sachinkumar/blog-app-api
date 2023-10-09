package com.ihrsachin.blogappapi.services;

import com.ihrsachin.blogappapi.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
