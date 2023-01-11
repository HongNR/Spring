package com.bs.spring.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring.member.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	private int boardNo;
	private String boardTitle;
//	private String boardWriter;
	private Member boardWriter;
	private String boardContent;
	private Date boardDate;
	private int boardReadcount;
	private List<Attachment> files;
}
