package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	List<Memo> selectMemoList();

	int insertMemo(Memo m);
	
	int selectMemoListCount();
	
	public List<Memo> selectMemoListPage(Map<String,Integer> param);
}
