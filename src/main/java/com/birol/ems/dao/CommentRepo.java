package com.birol.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birol.ems.dto.Comments;

public interface CommentRepo extends JpaRepository<Comments, String> {

}
