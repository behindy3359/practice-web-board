package com.practice.webboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.webboard.dto.CommentDTO;
import com.practice.webboard.entity.BoardEntity;
import com.practice.webboard.entity.CommentEntity;
import com.practice.webboard.repository.BoardRepository;
import com.practice.webboard.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public int save(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
		if (optionalBoardEntity.isPresent()) {
			BoardEntity boardEntity = optionalBoardEntity.get();
			CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);

			return commentRepository.save(commentEntity).getId();
		} else {
			return -1;
		}
	}

	public List<CommentDTO> findAll(int boardId) {
		// TODO Auto-generated method stub

		BoardEntity boardEntity = boardRepository.findById(boardId).get();
		List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
		List<CommentDTO> commentDTOList = new ArrayList<>();

		for (CommentEntity commentEntity : commentEntityList) {
			CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
			commentDTOList.add(commentDTO);
		}

		return commentDTOList;
	}
}
