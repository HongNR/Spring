package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

//Aspect로 등록하여 멤버메소드를 특정시점에
//  실행시키기!!
@Slf4j
public class LoggerAspect {
	
	//aspect클래스에서 메소드를 선언할 때는 지정 advisor에 따라 메소드 선언방식이 달라짐
	//타겟메소드가 실행되기 전에 실행하는 메소드 구현하기 -> Before
	public void loggerBefore(JoinPoint jp) {
		log.debug("loggerAspect실행함");
		//JoinPoint객체 aop설정에 의해 메소드가 실행될 때 정보를 확인할 수 있음
		//타겟클래스와 메소드 확인하기
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" : "+sig.getName());
		log.debug("==================");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("loggerAfter메소드 실행");
	}
	
}
