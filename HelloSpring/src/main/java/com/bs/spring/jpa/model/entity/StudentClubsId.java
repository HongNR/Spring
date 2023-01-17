package com.bs.spring.jpa.model.entity;

import java.io.Serializable;

import lombok.Data;

//복합키 식별자 클래스로
//복합키 식별자클래스는 조건이 있음
// 1. 기본생성자가 있어야한다.
// 2. 클래스가 public으로 선언되어야한다.
// 3. Serializable인터페이스를 구현해야한다.
// 4. equals, hascode메소드가 오버라이딩되어있어야한다.

@Data
public class StudentClubsId implements Serializable{
	//student, club을 복합키로 연결하는 클래스
	private long student;//StudentClub클래스의 Student클래스 필드명
	private long club;//StudentClub클래스의 Club클래스 필드명

}
