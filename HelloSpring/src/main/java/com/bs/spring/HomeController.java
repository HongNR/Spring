package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;
import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"loginMember"})
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//등록되어있는 springbean은 필드로 선언해서 사용 : Autowired
	// 서버가 생성될 때 만들어지게 되어있음
	
//	@Autowired
//	@Qualifier(value="alonge")//객체가 여러개일 때 하나만 지정해서 사용하기
//	private Animal a;
//
//	@Autowired
//	@Qualifier(value="dog")
//	private Animal b;
//	
//	@Autowired
//	@Qualifier(value="getDongmin")
//	private Person p;
//	
//	@Autowired(required=false)// 없으면 냅둬 but 잘 안씀
//	private Food food;
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IllegalAccessException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if(1==1) throw new IllegalAccessException("내맘대로 에러!!");
		
		return "home";
	}
	@RequestMapping("/")
	public String index(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		Cookie c=new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		session.setAttribute("sessionId", "admin");
		
		
		//등록된 springbean 출력하기
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");
		
//		System.out.println(a);
//		System.out.println("dog : "+b);
		
//		System.out.println(p);
//		
//		System.out.println(food);
		//메인화면을 출력해주는 mapping 메소드
		// /WEB-INF/views/return값.jsp 
		// -> request.getRequestDispatcher("/WEB-INF/views/return값.jsp ").foward(req,res);
		
		
		//Logger가 제공하는 메소드 이용해서 log 출력하기
		//메소드 : debug, info, warn, error
		//메소드는 출력되는 상황에 따라 결정해서 사용
		// debug : 개발 시에 사용하는 로그
		// info : 프로그램 실행중 사용자에게 전달해야하는 메세지 로그
		// warn : 비정상적으로 로직이 돌아갔을 때 경고 로그
		// error : 에러났을 때 ! 로그
		
		//logger태그에 설정되어있는 level에 따라 메소드 실행여부가 결정됨.
		//debug < info < warn < error
		
//		logger.debug("난 debug야");
//		logger.info("난 info야");
//		logger.warn("난 warn이야");
//		logger.error("난 error야");
		
		//logger로 다른 타입의 값 출력하기
//		logger.debug("food {} ", food);
		
		
		
		return "index";
	}
	
	@RequestMapping("/error.do")
	public String loginFail() {
		//인증실패 후 실행되는 메소드
		throw new AdminAccessException("로그인 실패");
	}
	
	@RequestMapping("/successLogin.do")
	public String successLogin(Model m) {
		//인증 후 실행되는 메소드
		Object o=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("{}",o);
		
		m.addAttribute("loginMember",(Member)o);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
}
