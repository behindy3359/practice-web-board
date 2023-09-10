package com.practice.webboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.webboard.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer>{

}
