package com.kh.ajax;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// Controller 및 웹 환경에서 사용되는 빈을 자동 생성 등록(@WebAppConfiguration 의존성 주입)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BoardControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testDoBoardInsert() {
		try {
			mockMvc.perform(post("/board/register").param("boardTitle", "JUnit-MockMVC Controller Test")
					.param("boardWriter", "MOCKMVC Writer3")
					.param("boardContents", "JUnit-MockMVC Board Register Test!")
					.param("boardFilename", "MOCKMVC start.png").param("boardFileRename", "202304101235.png")
					.param("boardFilepath", "/Users/shinheechae/git/KH_Ajax/SpringMVC_Ajax/src/main/webapp/resources/jUnitTest"))
			.andDo(print())
			.andExpect(status().isOk());
//			.andExpect(status().is(302));
			System.out.println(">>> 테스트 수행 성공 <<<");
		} catch (Exception e) {
			System.out.println(">>> 테스트 수행 실패 <<<" + e.getMessage());
		}
	}

}
