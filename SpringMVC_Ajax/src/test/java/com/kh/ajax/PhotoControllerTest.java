package com.kh.ajax;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class PhotoControllerTest {
	
	// HomeController의 org.slf4j		org.slf4j.LoggerFactory
	// log찍기 위해 사용
	private final static Logger logger = LoggerFactory.getLogger(PhotoControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		// .~ : log 레벨 지정
		// .debug : 디버깅 할때
		// .info : 서버 실행 할때
		logger.info("셋업 완료!!");
	}

	@Test
	public void testViewPhotoInsert() {
		try {
			mockMvc.perform(post("/photo/insert")
					.param("photoWriter", "chatGpt")
					.param("photoContent", "무엇이든 물어보살")
					.param("photoFilename", "WhatDoYouWant.png")
					.param("photoFileRename", "202304110935.png")
					.param("photoFilepath", "/Users/shinheechae/git/KH_Ajax/SpringMVC_Ajax/src/main/webapp/resources/jUnitTest"))
					.andDo(print())					// MockHttpServletRequest뜨게
					//.andExpect(status().is(302));	// 그린라이트 뜨게
					.andExpect(status().isOk());	// 그린라이트 뜨게
			// redirect의 경우 302
			// 성공 - 200, Not Found - 404, Internal Server Error - 500
			logger.info("테스트 수행 성공!");
		} catch (Exception e) {
			logger.info("테스트 수행 실패.. : " + e.getMessage());
		}
	}

}
