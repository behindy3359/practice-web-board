package com.practice.webboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.webboard.dto.CommentDTO;
import com.practice.webboard.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/save")
	public ResponseEntity saveComment(@ModelAttribute CommentDTO commentDTO) {

		System.out.println(commentDTO);
		int saveResult = commentService.save(commentDTO);

		if (saveResult != -1) {

			List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());

			return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}
	}
}
