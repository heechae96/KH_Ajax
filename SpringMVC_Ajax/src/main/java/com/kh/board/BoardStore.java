package com.kh.board;

import java.util.List;

import com.kh.ajax.domain.Reply;

public interface BoardStore {

	int insertBoard(Board board);

	List<Board> selectAll();

	Board selectOneByNo(int boardNo);

	int insertReply(Reply reply);

	List<Reply> selectAllReply(int boardNo);

}
