package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bs.spring.member.vo.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	//로그인 여부를 체크하는 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=(HttpSession)request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있는 서비스입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
}
