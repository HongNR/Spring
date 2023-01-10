package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.selectBoardCount");
	}
	
	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session,Map<String,Integer> param){
		return session.selectList("board.selectBoardList",null
				,new RowBounds((param.get("cPage")-1)*param.get("numPerpage")
						,param.get("numPerpage")));
	}
	
	@Override
	public Board selectBoardView(SqlSessionTemplate session,int boardNo) {
		return session.selectOne("board.selectBoardView",boardNo);
	}
}
