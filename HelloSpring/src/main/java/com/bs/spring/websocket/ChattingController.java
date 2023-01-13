package com.bs.spring.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChattingController {
	
	@RequestMapping("/chattingpage.do")
	public String chattingPage() {
		
		return "chatting/chat";
	}
}
