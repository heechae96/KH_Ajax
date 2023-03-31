package com.kh.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ajax.domain.Reply;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardStore bStore;

	@Override
	public int insertBoard(Board board) {
		int result = bStore.insertBoard(board);
		return result;
	}

	@Override
	public List<Board> selectAll() {
		List<Board> bList = bStore.selectAll();
		return bList;
	}

	@Override
	public Board selectOneByNo(int boardNo) {
		Board board = bStore.selectOneByNo(boardNo);
		return board;
	}

	@Override
	public int insertReply(Reply reply) {
		int result = bStore.insertReply(reply);
		return result;
	}

	@Override
	public List<Reply> selectAllReply(int boardNo) {
		List<Reply> rList = bStore.selectAllReply(boardNo);
		return rList;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = bStore.updateReply(reply);
		return result;
	}

	@Override
	public int deleteReply(int replyNo) {
		int result = bStore.deleteReply(replyNo);
		return result;
	}

}
