package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired	//service를 이용할거니까 autowired로 등록해주기
	private MemberService service;
	
	@RequestMapping("/test/")	//서블릿에서 doGet 역할
	public void test() {
		System.out.println("controller - test() 실행");
		service.test();
	}
}
