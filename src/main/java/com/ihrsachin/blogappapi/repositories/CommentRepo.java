package com.ihrsachin.blogappapi.repositories;

import com.ihrsachin.blogappapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}
