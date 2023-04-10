package com.kh.ajax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.board.Board;
import com.kh.board.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService bService;
	
	@Test
	public void testRegisterBoard() {
		Board board = new Board();
		board.setBoardTitle("Hello JUnit4 - 3!");
		board.setBoardContents("Hello Hi Board JUnit Test Go - 3!");
		board.setBoardWriter("admin3");
		board.setBoardFilename("JUnit4 Tutorial3.jpg");
		board.setBoardFileRename("202304101007.jpg");
		board.setBoardFilepath("/Users/shinheechae/git/KH_Ajax/SpringMVC_Ajax/src/main/webapp/resources/jUnitTest");
		// assertEquals(a,b) : b의 결과가 a이면 통과
		// 정상적으로 들어가면 result에 1이 들어감
		assertEquals(1, bService.insertBoard(board));
	}
	
	@Test
	public void testSelectAllBoard() {
		// 현재 8개가 DB에 들어가 있음
		assertEquals(8, bService.selectAll().size());
	}
	
	@Test
	public void testSelectOneBoard() {
		// assertNull : null이면 통과 null이 아니면 에러
		// 7번이 존재하기 때문에 에러
		// assertNull(bService.selectOneByNo(7));
		// 1번이 존재하지 않기때문에 통과
		// assertNull(bService.selectOneByNo(1));
		assertSame(7, bService.selectOneByNo(7).getBoardNo());
	}
	

}
