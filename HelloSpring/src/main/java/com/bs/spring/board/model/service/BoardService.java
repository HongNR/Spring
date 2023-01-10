package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.vo.Board;

public interface BoardService {
	int selectBoardCount();
	public List<Board> selectBoardList(Map<String,Integer> param);
	
	Board selectBoardView(int boardNo);
}
