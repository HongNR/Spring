package com.bs.spring.memo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.interceptor.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

@Controller
@RequestMapping("/memo")
public class MemoController {
	private MemoService service;
	@Autowired
	public MemoController(MemoService service) {
		this.service=service;
	}
	
										//Get방식으로 요청 했을 때만 받아오겠다.
	@RequestMapping(value="/memo.do", method= {RequestMethod.GET})
	public ModelAndView selectMemoList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1")int cPage,
			@RequestParam(value="numPerpage", defaultValue="5")int numPerpage) {	
		
		mv.addObject("memolist",service.selectMemoListPage(
				Map.of("cPage",cPage,"numPerpage",numPerpage))
				);
		//페이징처리하기
		int totalData=service.selectMemoListCount();
		
		mv.addObject("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,"memo.do"));
				
		
		mv.setViewName("memo/memoList");
		return mv;
	}
	
											//Post방식으로 요청 했을 때만 받아오겠다.
//	@RequestMapping(value="/insertMemo.do", method= {RequestMethod.POST})
	@PostMapping("/insertMemo.do")
	public String insertMemo(Memo memo, Model m) {
		int result=service.insertMemo(memo);
		
		m.addAttribute("loc","/memo/memo.do");
		m.addAttribute("msg",result>0?"메모등록성공":"메모등록실패");
		
		return "common/msg";
	}
	
}
