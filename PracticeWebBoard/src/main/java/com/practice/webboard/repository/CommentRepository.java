package com.practice.webboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.webboard.entity.BoardEntity;
import com.practice.webboard.entity.CommentEntity;


public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity); 
}
