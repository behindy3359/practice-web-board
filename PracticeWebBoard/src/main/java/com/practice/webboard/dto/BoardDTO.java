package com.practice.webboard.dto;

import java.time.LocalDateTime;

import com.practice.webboard.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
	private int id;
	
	private String boardWriter;
	private String boardPassword;
	private String boardTitle;
	private String boardContents;
	
	private int boardHits;
	
	private LocalDateTime boardCreatedTime;
	private LocalDateTime boardUpdatedTime;
	
	
	public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
		
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setId(boardEntity.getId());
		boardDTO.setBoardWriter(boardEntity.getBoardWriter());
		boardDTO.setBoardPassword(boardEntity.getBoardPassword());
		boardDTO.setBoardTitle(boardEntity.getBoardTitle());
		boardDTO.setBoardContents(boardEntity.getBoardContents());
		boardDTO.setBoardHits(boardEntity.getBoardHits());
		boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
		boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
		
		return boardDTO;
	}
}
