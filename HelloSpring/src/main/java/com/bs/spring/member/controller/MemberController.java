package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes({"loginMember"})//==model에 저장된 attribute 중 loginMember는 session이야
@RequestMapping("/member")//여기 컨트롤러 전체에 /member/ 라는 주소가 붙게하기
@Slf4j	//log 사용
public class MemberController {
	
	//private final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder; //암호화
	
	@Autowired	//service를 이용할거니까 autowired로 등록해주기
	public MemberController(MemberService service,BCryptPasswordEncoder passwordEncoder) {
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
	
//	@RequestMapping("/test/")	//서블릿에서 doGet 역할
//	public void test() {
//		System.out.println("controller - test() 실행");
//		service.test();
//	}
	
//	public String login(@RequestParam Map param) {
//	public String login(String userId,String password) {
	@RequestMapping("/loginMember.do")
//	public String login(Member m,HttpSession session) {
	public String login(Member m,Model model) {
		//Session에 데이터를 저장하고 관리
		Member loginMember=service.selecetMemberById(m);
		
		//암호화된 패스워드를 원본값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비교를 해야한다.
		//matches("원본값",암호화값)메소드를 이용
		if(loginMember!=null&&
				//loginMember.getPassword().equals(m.getPassword())
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			//로그인 성공
//			session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember",loginMember);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
	public String logout(SessionStatus session) {
//		session.invalidate();
		if(!session.isComplete()) {//session이 소멸되지 않았으면
			session.setComplete();	//session 삭제
		}
		
		return "redirect:/";
	}
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m,ModelAndView mv) {
		log.debug("파라미터로 전달된 member : {} ",m);
		
		//password 암호화 처리하기★
		String encodePassword=passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
		int result=service.insertMember(m);
		
		if(result>0) {
			mv.addObject("msg","회원가입 완료");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","회원가입 실패");
			mv.addObject("loc","/member/enrollMember.do");
		}
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/memberView.do")
	public String memberView(Member m,Model model) {
		Member viewMember=service.selecetMemberById(m);
		model.addAttribute("member",viewMember);
		
		return "member/memberView";
	}
	
}
