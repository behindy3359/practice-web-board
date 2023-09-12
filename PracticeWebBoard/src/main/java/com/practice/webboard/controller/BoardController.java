package com.practice.webboard.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.webboard.dto.BoardDTO;
import com.practice.webboard.dto.CommentDTO;
import com.practice.webboard.service.BoardService;
import com.practice.webboard.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final CommentService commentService;

	// About write on board
	@GetMapping("/save")
	public String saveForm() {

		return "/board/board-save-form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {

		boardService.save(boardDTO);

		return "index";
	}

	// About browse board
	@GetMapping("/list")
	public String findAll(Model model) {
		List<BoardDTO> boardDTOlist = boardService.findAll();
		model.addAttribute("boardList", boardDTOlist);

		return "/board/board-list";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable int id, Model model, @PageableDefault(page = 1) Pageable pageable) {

		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		List<CommentDTO>commentDTOList = commentService.findAll(id);
		
		model.addAttribute("commentList", commentDTOList);
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", pageable.getPageNumber());

		return "/board/board-detail";
	}

	// About update/modify board
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable int id, Model model) {

		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("boardUpdate", boardDTO);

		return "/board/board-update-form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {

		BoardDTO board = boardService.update(boardDTO);
		model.addAttribute("board", boardDTO);

		return "/board/board-detail";
	}

	// About delete board
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {

		boardService.delete(id);

		return "redirect:/board/list";
	}

	// About paging
	@GetMapping("/paging")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {

		int blockLimit = 3;
		Page<BoardDTO> boardList = boardService.paging(pageable);

		int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1
				: boardList.getTotalPages();

		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "/board/board-paging";
	}
}
