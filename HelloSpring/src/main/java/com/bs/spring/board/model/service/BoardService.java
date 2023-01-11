package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.vo.Board;

public interface BoardService {
	int insertBoard(Board b);
	List<Board> selectBoardList(Map<String,Integer> page);
	int selectBoardCount();
	Board selectBoard(int boardNo);
	
}
