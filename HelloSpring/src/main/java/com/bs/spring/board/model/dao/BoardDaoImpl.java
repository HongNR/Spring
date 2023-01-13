package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Override
	public int insertBoard(SqlSessionTemplate session,Board b) {
		return session.insert("board.insertBoard",b);
	}
	
	
	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session,Map<String,Integer> page){
		return session.selectList("board.selectBoardList",null
				,new RowBounds((page.get("cPage")-1)*page.get("numPerpage")
						,page.get("numPerpage")));
	}
	
	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.selectBoardCount");
	}
	
	@Override
	public Board selectBoard(SqlSessionTemplate session,int boardNo) {
		return session.selectOne("board.selectBoard",boardNo);
	}


	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment attachment) {
		return session.insert("board.insertAttachment",attachment);
	}
	
	
}
