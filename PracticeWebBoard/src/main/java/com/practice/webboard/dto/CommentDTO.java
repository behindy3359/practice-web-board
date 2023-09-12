package com.practice.webboard.dto;

import java.time.LocalDateTime;

import com.practice.webboard.entity.CommentEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {
	private int id;
	private String commentWriter;
	private String commentContents;
	private int boardId;
	private LocalDateTime commentCreatedTime;
	
	
	public static CommentDTO toCommentDTO(CommentEntity commentEntity, int boardId) {
		// TODO Auto-generated method stub
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setId(commentEntity.getId());
		commentDTO.setCommentWriter(commentEntity.getCommentWriter());
		commentDTO.setCommentContents(commentEntity.getCommentContents());
		commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
		commentDTO.setBoardId(boardId);
		
		return commentDTO;
	}
}
