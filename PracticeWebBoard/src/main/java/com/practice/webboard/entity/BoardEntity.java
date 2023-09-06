package com.practice.webboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.practice.webboard.dto.BoardDTO;

import lombok.Data;

@Entity
@Data
@Table(name="board_table")
public class BoardEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 40, nullable = false)
	private String boardWriter;
	
	@Column(length = 40, nullable = false)
	private String boardPassword;

	@Column(length = 40, nullable = false)
	private String boardTitle;
	
	@Column(length = 500)
	private String boardContents;

	@Column(length = 40)
	private int boardHits;
	
	
	public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPassword(boardDTO.getBoardPassword());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		
		return boardEntity;
	}
}
