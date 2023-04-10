package com.kh.ajax;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.photo.Photo;
import com.kh.photo.PhotoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class PhotoServiceImplTest {

	@Autowired
	private PhotoService pService;

	// 실습
	// 1. insertPhoto 메소드가 잘 동작하는지 테스트!

	@Test
	public void insertPhotoTest() {
		Photo photo = new Photo();
		photo.setPhotoWriter("JUnit4");
		photo.setPhotoContent("JUnit4 Photo Insert Test");
		photo.setPhotoFilename("JUnit4 Photo.png");
		photo.setPhotoFileRename("202304101113.png");
		photo.setPhotoFilepath("/Users/shinheechae/git/KH_Ajax/SpringMVC_Ajax/src/main/webapp/resources/jUnitTest");
		assertEquals(1, pService.insertPhoto(photo));
	}
	
	// 2. updatePhoto 비즈니스 로직 작성 후 JUnit 테스트!
	
	@Test
	public void updatePhotoTest() {
		Photo photo = new Photo();
		photo.setPhotoWriter("JUnit4 Test");	// 유니크 제약조건
		photo.setPhotoContent("JUnit4 Photo Update Test");
		photo.setPhotoFilename("JUnit4 Photo.png");
		photo.setPhotoFileRename("202304101145.png");
		photo.setPhotoFilepath("/Users/shinheechae/git/KH_Ajax/SpringMVC_Ajax/src/main/webapp/resources/jUnitTest");
		photo.setPhotoNo(25);
		assertEquals(1, pService.updatePhoto(photo));
	}

}
