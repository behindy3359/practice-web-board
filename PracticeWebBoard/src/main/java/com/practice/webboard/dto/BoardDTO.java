package com.practice.webboard.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.practice.webboard.entity.BoardEntity;
import com.practice.webboard.entity.BoardFileEntity;

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
	
	private List<MultipartFile> boardFile;
	private List<String> originalFileName;
	private List<String> storedFileName;
	private int fileAttached;
	
	public BoardDTO( int id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreaDateTime ) {
	
		this.id = id;
		this.boardWriter = boardWriter;
		this.boardTitle = boardTitle;
		this.boardHits = boardHits;
		this.boardCreatedTime = boardCreaDateTime;

	}
	
	
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
		
		if( boardEntity.getFileAttached() == 0 ) {
			boardDTO.setFileAttached(boardEntity.getFileAttached());
		}else{	
			List<String> originalFileNameList = new ArrayList<>();
			List<String> storedFileNameList = new ArrayList<>();
			
			
			boardDTO.setFileAttached(boardEntity.getFileAttached());
			
			for( BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList() ) {
				originalFileNameList.add(boardFileEntity.getOriginalFileName() );
				storedFileNameList.add(boardFileEntity.getStoredFileName());
			}
			boardDTO.setOriginalFileName(originalFileNameList);
			boardDTO.setStoredFileName(storedFileNameList);
		}
		
		return boardDTO;
	}
}
