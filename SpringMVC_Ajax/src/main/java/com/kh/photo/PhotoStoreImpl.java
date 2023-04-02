package com.kh.photo;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoStoreImpl implements PhotoStore{

	@Autowired
	private SqlSession session;

	@Override
	public int insertPhoto(Photo photo) {
		int result = session.insert("photoMapper.insertPhoto", photo);
		return result;
	}

	@Override
	public List<Photo> morePhoto(int start) {
		int limit = 5;
		int offset = (start - 1) * limit;
		RowBounds rowbounds = new RowBounds(offset, limit);
		List<Photo> pList = session.selectList("photoMapper.morePhoto", null, rowbounds);
		return pList;
	}
}
