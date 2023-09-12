package com.practice.webboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.practice.webboard.dto.CommentDTO;

import lombok.Data;

@Entity
@Data
@Table(name="comment_table")
public class CommentEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column( length =20, nullable = false)
	private String commentWriter;
	
	@Column
	private String commentContents;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "board_id")
	private BoardEntity boardEntity;

	public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
		// TODO Auto-generated method stub
		CommentEntity commentEntity= new CommentEntity();
		commentEntity.setCommentWriter(commentDTO.getCommentWriter());
		commentEntity.setCommentContents(commentDTO.getCommentContents());
		commentEntity.setBoardEntity(boardEntity);
		
		return commentEntity;
	}
}
