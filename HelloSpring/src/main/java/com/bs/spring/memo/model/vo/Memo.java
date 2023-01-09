package com.bs.spring.memo.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Memo {
	private int memoNo;
	private String memo;
	private String password;
	private Date memoDate;
}
