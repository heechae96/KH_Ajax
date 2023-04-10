package com.kh.photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoStore pStore;

	@Override
	public int insertPhoto(Photo photo) {
		int result = pStore.insertPhoto(photo);
		return result;
	}
	
	@Override
	public int updatePhoto(Photo photo) {
		int result = pStore.updatePhoto(photo);
		return result;
	}

	@Override
	public List<Photo> morePhoto(int start) {
		List<Photo> pList = pStore.morePhoto(start);
		return pList;
	}

}
