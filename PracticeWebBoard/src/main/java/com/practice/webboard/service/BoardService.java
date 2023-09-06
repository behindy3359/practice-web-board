package com.practice.webboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.webboard.dto.BoardDTO;
import com.practice.webboard.entity.BoardEntity;
import com.practice.webboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	
	//About write on Board
	public void save(BoardDTO boadrdDTO) {
		BoardEntity boardEntity = BoardEntity.toSaveEntity(boadrdDTO);
		boardRepository.save(boardEntity);
	}
	

	//About browse board
	public List<BoardDTO> findAll(){
		
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for(BoardEntity boardEntity : boardEntityList) {
			boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
		}
		return boardDTOList;
	}
}
