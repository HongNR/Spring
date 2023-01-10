package com.bs.spring.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.interceptor.PageFactory;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	@RequestMapping("/board.do")
	public ModelAndView selectBoardList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1")int cPage,
			@RequestParam(value="numPerpage", defaultValue="10")int numPerpage) {
		
		mv.addObject("boardList",service.selectBoardList(
				Map.of("cPage",cPage,"numPerpage",numPerpage))
				);
		//페이징처리
		int totalData=service.selectBoardCount();
		mv.addObject("totalContents",totalData);
		mv.addObject("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,"board.do"));
		
		mv.setViewName("board/boardlist");
		
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public String boardView(int boardNo,Model model) {
		Board result=service.selectBoardView(boardNo);
		model.addAttribute("boardView",result);
		
		return "board/boardView";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard() {
		
		return "board/insertBoard";
	}
}
