package com.kh.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ajax.domain.Reply;

@Repository
public class BoardStoreImpl implements BoardStore{
	
	@Autowired
	private SqlSession session;

	@Override
	public int insertBoard(Board board) {
		int result = session.insert("boardMapper.insertBoard", board);
		return result;
	}

	@Override
	public List<Board> selectAll() {
		List<Board> bList = session.selectList("boardMapper.selectAll");
		return bList;
	}

	@Override
	public Board selectOneByNo(int boardNo) {
		Board board = session.selectOne("boardMapper.selectOneByNo", boardNo);
		return board;
	}

	@Override
	public int insertReply(Reply reply) {
		int result = session.insert("replyMapper.insertReply", reply);
		return result;
	}

	@Override
	public List<Reply> selectAllReply(int boardNo) {
		List<Reply> rList = session.selectList("replyMapper.selectAllReply", boardNo);
		return rList;
	}

}
