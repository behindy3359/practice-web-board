package com.practice.webboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;

import com.practice.webboard.dto.BoardDTO;

import lombok.Data;

@Entity
@Data
@Table( name="board_table" )
public class BoardEntity extends BaseEntity{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY  )
	private int id;

	@Column( length = 40, nullable = false )
	private String boardWriter;
	
	@Column( length = 40, nullable = false )
	private String boardPassword;

	@Column( length = 40, nullable = false )
	private String boardTitle;
	
	@Column( length = 500 )
	private String boardContents;

	@Column( length = 40 )
	private int boardHits;
	
	@Column
	private int fileAttached;
	
	@OneToMany( mappedBy = "boardEntity", cascade=CascadeType.REMOVE,  orphanRemoval = true, fetch = FetchType.LAZY )
	private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
	
	public static BoardEntity toSaveEntity( BoardDTO boardDTO ) {
		
		BoardEntity boardEntity = new BoardEntity();
		
		boardEntity.setBoardWriter( boardDTO.getBoardWriter() );
		boardEntity.setBoardPassword( boardDTO.getBoardPassword() );
		boardEntity.setBoardTitle( boardDTO.getBoardTitle() );
		boardEntity.setBoardContents( boardDTO.getBoardContents() );
		boardEntity.setBoardHits( 0 );
		boardEntity.setFileAttached (0 );
		
		return boardEntity;
	}
	
	public static BoardEntity toSaveFileEntity( BoardDTO boardDTO ) {
		
		BoardEntity boardEntity = new BoardEntity();
		
		boardEntity.setBoardWriter( boardDTO.getBoardWriter() );
		boardEntity.setBoardPassword( boardDTO.getBoardPassword() );
		boardEntity.setBoardTitle( boardDTO.getBoardTitle() );
		boardEntity.setBoardContents( boardDTO.getBoardContents() );
		boardEntity.setBoardHits( 0 );
		boardEntity.setFileAttached ( 1 );
		
		return boardEntity;
	}



	public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
		
		BoardEntity boardEntity = new BoardEntity();
		
		boardEntity.setId(boardDTO.getId());
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPassword(boardDTO.getBoardPassword());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits( boardDTO.getBoardHits() );
		
		return boardEntity;
	}
}
