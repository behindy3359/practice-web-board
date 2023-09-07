package com.practice.webboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	public void save( BoardDTO boadrdDTO ) {
		BoardEntity boardEntity = BoardEntity.toSaveEntity( boadrdDTO );
		boardRepository.save( boardEntity );
	}
	

	//About browse board
	public List<BoardDTO> findAll(){
		
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for( BoardEntity boardEntity : boardEntityList ) {
			boardDTOList.add( BoardDTO.toBoardDTO( boardEntity ) );
		}
		return boardDTOList;
	}
	@Transactional
	public void updateHits( int id ) {
		boardRepository.updateHits( id );
		
	}	
	public BoardDTO findById( int id ) {
		
		Optional< BoardEntity > optionalBoardEntity = boardRepository.findById( id );
		if( optionalBoardEntity.isPresent() ) {
			
			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
			return boardDTO;
		}else {
			return null;
		}
	}
	

	//About modify/ update board
	public BoardDTO update(  BoardDTO boardDTO ) {
		
		BoardEntity boardEntity = BoardEntity.toUpdateEntity( boardDTO );
		boardRepository.save(boardEntity);
		
		return findById( boardDTO.getId() );
	}


	//About delete board
	public void delete(int id) {
		// TODO Auto-generated method stub
		boardRepository.deleteById( id );
	}
	
	
}
