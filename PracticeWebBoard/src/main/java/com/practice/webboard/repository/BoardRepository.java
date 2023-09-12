package com.practice.webboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.webboard.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

	@Modifying
	@Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id=:id")
	void updateHits(@Param("id") int id);

}
