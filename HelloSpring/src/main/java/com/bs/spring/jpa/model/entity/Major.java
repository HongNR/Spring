package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="seq_majorno", sequenceName="seq_majorno"
	,initialValue = 1,allocationSize = 1)
public class Major {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq_majorno")
	private Long majorNo;
	
	private String majorName;
	private String professor;
	
	//연관관계를 설정하는 어노테이션 추가
	//서브클래스는 mappedBy속성을 이용해서 주가 되는 클래스를 참조
	@OneToMany(mappedBy="major")//주클래스의 참조필드명을 mappedBy에 작성하면됨
	private List<JpaMember> members;
}
