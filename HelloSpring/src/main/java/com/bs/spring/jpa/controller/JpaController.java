package com.bs.spring.jpa.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.MemberLevel;
import com.bs.spring.jpa.model.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JpaController {

	private JpaService service;

	@Autowired
	public JpaController(JpaService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/jpa/insert")
	public String insertMember(String userId) {
		JpaMember m=JpaMember.builder().memberId(userId)
				.memberPwd("1234").memberLevel(MemberLevel.GOLD)
				.age(19).height(180.5).enrollDate(new Date())
				.intro("하하하하 벌써 1이 반지났네..").build();
		
		service.insertMember(m);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/members")
	public String selectMemberAll() {
		List<JpaMember> list=service.selectMemberAll();
		log.debug("{}",list);
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/member")
	public String selectMemberById(long id) {
		
		JpaMember m=service.selectMemberById(id);
		
		log.debug("{}",m);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/update")
	public String updateMember(long no,int age,double height,String intro) {
		Map<String,Object> param=Map.of("age",age,"height",height,"intro",intro);
		service.updateMember(param, no);
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/delete")
	public String deleteMember(long no) {
		service.deleteMember(no);
		return "redirect:/";
	}
	
	
	
	
}
