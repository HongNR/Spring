package com.bs.spring.jpa.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
//복합키를 설정할때는 식별자 클래스가 필요함...
@IdClass(StudentClubsId.class)
public class StudentClubs {
	
	@Id
	@ManyToOne
	@JoinColumn(name="studentNo")
	private Student student;
	
	@Id
	@ManyToOne
	@JoinColumn(name="clubNo")
	private Club club;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
}
