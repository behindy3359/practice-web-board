package com.practice.webboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public String findById(@PathVariable int id, Model model) {
		
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		
		return "/board/board-detail";
	}
	
	//About update/modify board
	@GetMapping("/update/{id}")
	public String updateForm( @PathVariable int id, Model model ) {
		
		System.out.println( "id : "+id );
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute( "boardUpdate", boardDTO );
		
		return "/board/board-update-form";
	}
	@PostMapping( "/update" )
	public String update( @ModelAttribute BoardDTO boardDTO , Model model) {
		
		BoardDTO board = boardService.update( boardDTO );
		model.addAttribute("board", boardDTO);
		
		return "/board/board-detail";
	}
	
	//About delete board
	@GetMapping( "/delete/{id}" )
	public String delete( @PathVariable int id ) {
		boardService.delete( id );
		return  "redirect:/board/list";
	}
}
