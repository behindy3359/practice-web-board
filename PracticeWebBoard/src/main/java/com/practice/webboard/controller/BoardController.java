package com.practice.webboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.webboard.dto.BoardDTO;
import com.practice.webboard.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	// About write on board 	
	@GetMapping("/save")
	public String saveForm() {
		
		return "/board/board-save-form";
	}
	@PostMapping("/save")
	public String save( @ModelAttribute BoardDTO boardDTO ) {
		
		System.out.println("boardDTO = "+ boardDTO);
		boardService.save(boardDTO);
		
		
		return "index";
	}

	//About browse board
	@GetMapping("/list")
	public String findAll( Model model ) {
		List<BoardDTO> boardDTOlist =  boardService.findAll();
		model.addAttribute( "boardList" , boardDTOlist);
		
		return "/board/board-list";
	}
}
