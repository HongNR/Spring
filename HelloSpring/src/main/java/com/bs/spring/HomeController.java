package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//등록되어있는 springbean은 필드로 선언해서 사용
	@Autowired
	@Qualifier(value="alonge")
	private Animal a;

	@Autowired
	@Qualifier(value="dog")
	private Animal b;
	
	@Autowired
	private Person yeonji;
	
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("/")
	public String index() {
		//등록된 springbean 출력하기
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");
		
//		System.out.println(a);
//		System.out.println("dog : "+b);
		
		System.out.println(yeonji);
		
		//메인화면을 출력해주는 mapping 메소드
		// /WEB-INF/views/return값.jsp 
		// -> request.getRequestDispatcher("/WEB-INF/views/return값.jsp ").foward(req,res);
		
		return "index";
	}
}
